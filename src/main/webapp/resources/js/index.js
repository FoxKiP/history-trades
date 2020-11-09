let selectEmitent, date, dataTableApi, securityForm, modalSecurity, historyForm, modalHistory;

$(function () {
    dataTableApi = $("#datatable").DataTable({
        "paging":   false,
        "info":     false,
        "autoWidth": false,
        "columns": [
            {
                "data": "idOnExchange"
            },
            {
                "data": "regNumber"
            },
            {
                "data": "name"
            },
            {
                "data": "emitentTitle"
            },
            {
                "data": "tradeDate",
                render: renderDate
            },
            {
                "data": "numTrades"
            },
            {
                "data": "open"
            },
            {
                "data": "close"
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                render: renderEditButton
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                render: renderDeleteButton
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });

    modalSecurity = $('#editSecurity');
    securityForm = $('#securityForm');
    modalHistory = $('#editHistory');
    historyForm = $('#historyForm');
    selectEmitent = $("#select-emitent");
    date = $('#date');

    $("#search").on("keyup", function () {
        dataTableApi.search(this.value).draw();
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    updateDropdownList();
    updateTable();
});

function renderDate (data) {
    if(data) {
        const dm = moment({y: data[0], M: data[1] - 1, d: data[2]});
        return dm.format("YYYY-MM-DD");
    } else {
        return "";
    }
}

function renderEditButton (data, type, row) {
    if(type === "display") {
        return `<button class='btn btn-xs btn-primary' onclick='editHistory(${row.historyId})'>Edit</button>`;
    } else {
        return data;
    }
}

function renderDeleteButton (data, type, row) {
    if(type === "display") {
        return `<button class='btn btn-xs btn-danger' onclick="deleteHistory(${row.historyId})">Delete</button>`
    } else {
        return data;
    }
}

function updateDropdownList() {
    $.get("ajax/security").done(function (dropdownList) {
        const val = selectEmitent.val();
        selectEmitent.empty();
        selectEmitent.append(`<option value="all">Select emitent</option>`);
        new Map(Object.entries(dropdownList)).forEach((value, key) => {
            selectEmitent.append(`<option value=${value}>${key}</option>`);
        });
        if(val != null) {
            $(`#select-emitent option[value=${val}]`).prop('selected', true);
        }
    });
}

function updateTable() {
    $.get("ajax/history/filter",
        {securityId: checkSelectValue(selectEmitent.val()),
        tradeDate: date.val()})
        .done(function (table) {
            dataTableApi.clear();
            dataTableApi.rows.add(table);
            dataTableApi.draw();
        });
}

function checkSelectValue(value) {
    if(value === "all") {
        return null;
    } else {
        return value;
    }
}

function createHistory() {
    historyForm.find("input").val("");
    const securityId = checkSelectValue(selectEmitent.val());
    if(securityId != null) {
        historyForm.find("input[name='securityId']").val(securityId);
        modalHistory.modal();
    } else {
        infoNoty("Select emitent please")
    }
}

function createSecurity() {
    securityForm.find("input").val("");
    $('#editSecurity').modal();
}

function editHistory(id) {
    $.get("ajax/history/" + id).done(function (data) {
            modalHistory.find("input[name='securityId']").val(data.security.id)
            $.each(data, function (key, value) {
                const row = historyForm.find("input[name=" + key + "]");
                if(key === "tradeDate") {
                    row.val(renderDate(value));
                } else {
                    row.val(value);
                }
            });
        modalHistory.modal();
        }
    );
}

function saveHistory() {
    $.ajax({
        type: "POST",
        url: "ajax/history/",
        data: historyForm.serialize()
    }).done(function () {
        updateTable();
        modalHistory.modal("hide");
        successNoty("History saved")
    });
}

function deleteHistory(id) {
    if(confirm('Are you sure?')) {
        $.ajax({
            type: "POST",
            url: "ajax/history/delete/" + id,
        }).done(function () {
            updateTable();
            successNoty("History deleted")
        });
    }
}


function deleteSecurity() {
    const securityId = checkSelectValue(selectEmitent.val());
    if(securityId != null) {
        if (confirm('Are you sure?')) {
            $(`#select-emitent option[value="all"]`).prop('selected', true);
            $.ajax({
                type: "POST",
                url: "ajax/security/delete/" + securityId,
            }).done(function () {
                updateDropdownList();
                updateTable();
                successNoty("Security deleted")
            });
        }
    } else {
        infoNoty("Select emitent please")
    }

}

function saveSecurity() {
    $.ajax({
        type: "POST",
        url: "ajax/security/",
        data: securityForm.serialize()
    }).done(function () {
        updateDropdownList()
        updateTable();
        modalSecurity.modal("hide");
        successNoty("Security saved")
    });
}

function editSecurity() {
    const securityId = checkSelectValue(selectEmitent.val());
    if(securityId != null) {
        $.get("ajax/security/" + securityId).done(function (data) {
                $.each(data, function (key, value) {
                    const row = securityForm.find("input[name=" + key + "]");
                    row.val(value);
                });
                modalSecurity.modal();
            }
        );
    } else {
        infoNoty("Select emitent please")
    }

}

function uploadSecurities() {
    const formData = new FormData($("#uploadSecurity")[0]);

    $.ajax({
        url: "ajax/security/upload",
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function () {
        updateDropdownList();
        successNoty("Securities uploaded");
    });
}

function uploadHistories() {
    const formData = new FormData($("#uploadHistory")[0]);

    $.ajax({
        url: "ajax/history/upload",
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function () {
        updateTable();
        successNoty("Histories uploaded");
    });
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1200
    }).show();
}

function infoNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'info',
        layout: "bottomRight",
        timeout: 1200
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    }).show();
}