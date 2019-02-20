$(document).ready(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Documents':'#', 'Document Manager':'#'});
	
	
	/*chosen-select*/
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	$('.documentCategoryLi').on('click', function() {
		var classActive = 'btn-warning';
		var documentCategoryAbbr = $(this).data('document-category-abbr');
		$('.documentCategoryLi').each(function(i, obj) {
			$(obj).removeClass(classActive);
		});
		$(this).addClass(classActive);
	});
	
	loadFiles();
});


function loadFiles(){
	var documentOwnerPartnerId = $('#documentOwnerPartnerId').val();
	var documentCategoryId = $('.documentCategoryLi.btn-warning').data('document-category-id');
	
	var URL = contextRoot + 'documents/documentManagerController/getDocumentsListAjaxPageForDocumentManager';
	
	$.ajax({
		url : URL,
		data : 'documentOwnerPartnerId='+documentOwnerPartnerId+'&documentCategoryId='+documentCategoryId,
		method : 'post',
		success : function(data){
			//alert(data);
		}
	});
}