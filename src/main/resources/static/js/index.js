$(function () {

    $.extend($.jgrid.defaults, {
        datatype: 'json',
        jsonReader: {
            repeatitems: false,
            root: "booksData",
            page: "currentPage",
            total: "totalPages",
            records: "totalRecords"
        },
        /*                prmNames: {
                            page: "page.page",
                            rows: "page.size",
                            sort: "page.sort",
                            order: "page.sort.dir"
                        },*/
        sortname: 'title',
        sortorder: 'asc',
        height: 'auto',
        viewrecords: true,
        rowList: [10, 20, 30],
        altRows: true,
        loadError: function (xhr, status, error) {
            alert(error);
        }
    });

    $.extend($.jgrid.edit, {
        closeAfterEdit: true,
        closeAfterAdd: true,
        ajaxEditOptions: {contentType: "application/json"},
        mtype: 'PUT',
        serializeEditData: function (data) {
            delete data.oper;
            return JSON.stringify(data);
        }
    });
    $.extend($.jgrid.del, {
        mtype: 'DELETE',
        serializeDelData: function () {
            return "";
        }
    });

    var editOptions = {
        onclickSubmit: function (params, postdata) {
            params.url = URL + '/' + postdata.id;
        }
    };
    var addOptions = {mtype: "POST"};

    var delOptions = {
        onclickSubmit: function (params, postdata) {
            params.url = URL + '/' + postdata;
        }
    };

    var URL = '/api/book';

    var options = {
        url: URL,
        editurl: URL,
        colModel: [
            {
                label: 'ID',
                name: 'id',
                key: true,
                formatter: 'integer',
                editable: true,
                index: 'id',
                editoptions: {disabled: true, size: 5},
                width: 40
            },
            {
                label: 'ISBN',
                name: 'isbn',
                index: 'isbn',
                editable: true,
                editrules: {required: true},
                width: 100
            }
            ,
            {
                label: 'Title',
                name: 'title',
                index: 'title',
                editable: true,
                editrules: {required: true},
                width: 350
            },
            {
                label: 'Genre',
                name: 'genre',
                index: 'genre',
                editable: true,
                editrules: {required: true},
                width: 60
            },
            {
                label: 'Author',
                name: 'author',
                index: 'author',
                editable: true,
                editrules: {required: true},
                width: 300
            }
        ],
        caption: "Books",
        rowNum: 10,
        hidegrid: false,
        guiStyle: "bootstrap4",
        iconSet: "fontAwesome",
        loadonce: false,
        pager: '#pager',
        emptyrecords: "Nothing to display",
        sortname: 'id',
        sortorder: 'asc',
        viewrecords: true,
        gridview: true,
        height: 'auto',
        ondblClickRow: function (id) {
            jQuery(this).jqGrid('editGridRow', id, editOptions);
        }
    };

    $("#list")
        .jqGrid(options)
        .navGrid('#pager',
            {}, //options
            editOptions,
            addOptions,
            delOptions,
            {} // search options
        );

});

