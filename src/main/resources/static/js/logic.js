var categoryList = {};
$( document ).ready(function() {
    $.ajax({
        url: window.location.href+"rest/api/categoryList"
    }).then(function(response) {
        for (var i = 0; i < response.length; i++) {
            categoryList[response[i].id] = response[i].name;
        }
        $.ajax({
            url: window.location.href+"rest/api/products"
        }).then(function(response) {
            for (var i = 0; i < response.length; i++) {
                drawRow(response[i]);
            }
        });
    });
    //editing row
    var $tableBody = $("#table-body");
    $tableBody.on('click', '.Edit', function() {
        var par = $(this).closest("tr");
        var tdIdVal = par.children("td:nth-child(1)").html();
        var tdName = par.children("td:nth-child(2)");
        var tdCategoryId = par.children("td:nth-child(3)");
        var tdPrice = par.children("td:nth-child(4)");
        var tdEditButton = $('#edit-id-'+tdIdVal);
        tdName.html("<input type='text' value='"+tdName.html()+"'/>");
        var selectedValue = 0;
        var select = $('<select />');
        for(var val in categoryList) {
            $('<option />', {id: val, text: categoryList[val]}).appendTo(select);
            if (categoryList[val] == tdCategoryId.html()) selectedValue = parseInt(val);
        }
        select.val(selectedValue);
        tdCategoryId.replaceWith(select);
        tdPrice.html("<input type='text' value='"+tdPrice.html()+"'/>");
        tdEditButton.addClass('Save').removeClass('Edit').prop('value', 'Save');

    });

    //deleting row
    $tableBody.on('click', '.Delete', function() {
        var par = $(this).closest("tr");
        var tdIdVal = parseInt(par.children("td:nth-child(1)").html());
        if(tdIdVal){
            $.ajax({
                type: "DELETE",
                url: window.location.href+"rest/api/products/"+tdIdVal
            }).then(function(response) {
                par.remove();
            });
        }else {
            par.remove();
        }
    });

    //saving row
    $tableBody.on('click', '.Save', function() {
        var par = $(this).closest("tr");
        var tdIdVal = par.children("td:nth-child(1)").html();
        var tdName = par.children("td:nth-child(2)");
        var tdCategorySelect = par.children("select");
        var tdCategorySelectedIndex = par.children("select")[0].selectedIndex;
        var tdCategoryId = par.children("select")[0][tdCategorySelectedIndex].id;
        var tdCategoryName = par.children("select")[0][tdCategorySelectedIndex].value;
        var tdPrice= par.children("td:nth-child(4)");
        var tdEditButton = tdIdVal ? $('#edit-id-'+tdIdVal):$('#edit-id-');
        var tdNameVal = tdName.children("input[type=text]").val();
        var tdPriceVal = tdPrice.children("input[type=text]").val();

        tdName.html(tdName.children("input[type=text]").val());
        tdCategorySelect.replaceWith(($('<td />', { text:  categoryList[tdCategoryId]})));
        tdPrice.html(tdPrice.children("input[type=text]").val());
        tdEditButton.addClass('Edit').removeClass('Save').prop('value', 'Edit');
        var product = {
            name:tdNameVal,
            category:{name:tdCategoryName},
            price:parseFloat(tdPriceVal)
        };
        if (tdIdVal){
            product.id = parseInt(tdIdVal);
            $.ajax({
                type: "PUT",
                data: JSON.stringify(product),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: window.location.href+"rest/api/products/"+product.id
            }).then(function(product) {});
        }else {
            $.ajax({
                type: "POST",
                data: JSON.stringify(product),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: window.location.href+"rest/api/products"
            }).then(function(product) {
                par.children("td:nth-child(1)").html(product.id);
                tdEditButton.attr('id','edit-id-'+product.id);
            });
        }
    });
});

//building the row
function drawRow(rowData) {
    var container = $('#table-body');
    var $trs = $();
    //Create TR and append TDs to it
    var $tr = $('<tr/>', {id: 'table-row-id-'+rowData.id});
    $tr.append(
        $('<td />', {id: 'id-'+rowData.id, value: rowData.id, text: rowData.id}).
        add($('<td />', { text: rowData.name})).
        add($('<td />', { text: categoryList[rowData.category]||rowData.category.name})).
        add($('<td />', { text: rowData.price})).
        add($('<input />', { type: "button", id: 'edit-id-'+rowData.id, value: "Edit" ,class: 'Edit'})).
        add($('<input />', { type: "button", id: 'delete-id-'+rowData.id, value: "Delete" , class:'Delete'}))
    );
    //Add each tr to the container
    $trs = $trs.add($tr);

    //Append all TRs to the container.
    container.append($trs);


}

//add new row button
function add(){
    var container = $('#table-body');
    var $trs = $();
    //Create TR and append TDs to it
    var $tr = $('<tr/>', {id: 'table-row-id-'});
    //Create dropdown
    var select = $('<select />');
    for(var val in categoryList) {
        $('<option />', {id: val, text: categoryList[val]}).appendTo(select);
    }
    $tr.append(
        $('<td />').
        add($('<td />').append($('<input />', { type: "text"}))).
        add(select).
        add($('<td />').append($('<input />', { type: "text"}))).
        add($('<input />', { type: "button", id: 'edit-id-', value: "Save" ,class: 'Save'})).
        add($('<input />', { type: "button", id: 'delete-id-', value: "Delete" ,class: 'Delete'}))
    );
    //Add each tr to the container
    $trs = $trs.add($tr);

    //Append all TRs to the container.
    container.append($trs);
}







