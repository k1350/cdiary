function addOnclickToDatatableRows() {
    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
        .getElementsByTagName('tr');
    for (var i = 0; i < trs.length; i++) {
        trs[i].onclick = new Function("selectRow(this)");
    }
}

function selectRow(tr) {
    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
        .getElementsByTagName('tr');
    for (var i = 0; i < trs.length; i++) {
        if (trs[i] == tr) {
            document.getElementById('form:rowIndex').value = i;
            document.getElementById('form:hiddenBtn').click();
        } else {
            trs[i].bgColor = '#ffffff';
        }
    }
}

