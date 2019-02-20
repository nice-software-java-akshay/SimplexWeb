$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'PO':'#', 'Purchase Order History':'#'});
	
	/*chosen-select*/
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	$('.dataTables-example').DataTable({
        pageLength: 25,
        responsive: true,
        fnDrawCallback: function() {
            $(".dataTables-example > thead").remove();
          }
    });
	
});