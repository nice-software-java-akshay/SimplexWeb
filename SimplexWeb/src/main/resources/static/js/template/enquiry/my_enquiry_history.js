$(function(){
	//Compulasory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Enquiry':'#', 'My Enquiry History':'#'});
	
	//Data table
	$('.dataTables-example').DataTable({
        pageLength: 25,
        responsive: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'csv',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'excel', title: 'Distributer List',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'pdf', title: 'Distributer List',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },

            {extend: 'print',
                 customize: function (win){
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                                .addClass('compact')
                                .css('font-size', 'inherit');
                },
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            }
        ]

    });
	
	//View enquiry button
	$('.viewEnquiryBtn').on('click', function() {
		var ENQUIRY_ID = $(this).data('enquiryid');
		var ENQUIRY_NUMBER = $(this).data('enquirynumber');
		
		var URL = contextRoot + 'enquiry/globalTemplateController/getGlobalTemplateQuotation?enquiryId='+ENQUIRY_ID+'&enquiryNumber='+ENQUIRY_NUMBER;
		var win = window.open(URL);
		if (win) {
		    //Browser has allowed it to be opened
		    win.focus();
		} else {
		    //Browser has blocked it
		    alert('Please allow popups for this website');
		}
	})
});