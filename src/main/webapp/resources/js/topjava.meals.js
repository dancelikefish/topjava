let ajaxUrl = "ajax/meals/";
$(function () {
    makeEditable({
            ajaxUrl: ajaxUrl,
            datatableApi: $("#mealtable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
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
                        "desc"
                    ]
                ]
            })
        }
    );
});

function filterTable() {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: ajaxUrl + "filter",
        data: $('#filter').serialize(),
    }).done(updateTableWithData(data));
}

function cancelFilter() {
    $('#filter')[0].reset();
        $.ajax({
            type: "GET",
            url: ajaxUrl,
        }).done(updateTableWithData(data));
}