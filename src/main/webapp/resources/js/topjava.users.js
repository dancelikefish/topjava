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
            data: {strID:id, strState:"0"},
        }).done(function () {
            updateTable();
            successNoty("Succeed");
        });
    } else {
        $.ajax({
            type: "POST",
            url: ajaxUrl + id,
            data: {strID:id, strState:"1"},
        }).done(function () {
            updateTable();
            successNoty("Succeed");
        });
    }
}