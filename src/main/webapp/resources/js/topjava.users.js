// $(document).ready(function () {
let ajaxUrl = "ajax/admin/users/";
$(function () {
    makeEditable({
            ajaxUrl: ajaxUrl,
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
});

function changeActivity(id) {
    let checkBox = $("#activityCheckbox");
    if (checkBox.is(":checked")) {
        $.ajax({
            type: "POST",
            url: ajaxUrl + id,
            data: {id:id,enableState:true},
        }).done(doIfDone());
    } else {
        $.ajax({
            type: "POST",
            url: ajaxUrl + id,
            data: {id:id,enableState:false},
        }).done(doIfDone());
    }
}

function doIfDone() {
    updateTable();
    successNoty("Succeed");
}