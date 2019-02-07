$(document).ready(function(){
	//Compulasory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Template':'#', 'Global Template':'#'});
	
    $("#globalTemplateForm").steps({
        bodyTag: "fieldset",
        onStepChanging: function (event, currentIndex, newIndex)
        {
            // Always allow going backward even if the current step contains invalid fields!
            if (currentIndex > newIndex)
            {
                return true;
            }

            // Forbid suppressing "Warning" step if the user is to young
            if (newIndex === 3 && Number($("#age").val()) < 18)
            {
                return false;
            }

            var form = $(this);

            // Clean up if user went backward before
            if (currentIndex < newIndex)
            {
                // To remove error styles
                $(".body:eq(" + newIndex + ") label.error", form).remove();
                $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
            }

            // Disable validation on fields that are disabled or hidden.
            form.validate().settings.ignore = ":disabled,:hidden:not(.chosen-select)";

            // Start validation; Prevent going forward if false
            return form.valid();
        },
        onStepChanged: function (event, currentIndex, priorIndex)
        {
            // Suppress (skip) "Warning" step if the user is old enough.
            if (currentIndex === 2 && Number($("#age").val()) >= 18)
            {
                $(this).steps("next");
            }

            // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
            if (currentIndex === 2 && priorIndex === 3)
            {
                $(this).steps("previous");
            }
        },
        onFinishing: function (event, currentIndex)
        {
            var form = $(this);

            // Disable validation on fields that are disabled.
            // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
            form.validate().settings.ignore = ":disabled,:hidden:not(.chosen-select)";

            // Start validation; Prevent form submission if false
            return form.valid();
        },
        onFinished: function (event, currentIndex)
        {
            var form = $(this);

            // Submit form input
            form.submit();
        }
    }).validate({
        errorPlacement: function (error, element)
        {
            element.before(error);
        },
        rules: {
            confirm: {
                equalTo: "#password"
            }
        }
    });
    
  //Assign values to dom elements defined above
    setProductGlobalVariables();
    
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-orange',
        radioClass: 'iradio_square-orange',
    });
    
  //Chosen Select Autocomplete
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	/**
	 * 
	 * Set variables values and initial state - START 
	 * NOTE: Order is important
	 * 
	 * */
		//1. Set Global variables values
		setAllGlobalVariables();
	
		//2. Set Initial Show/Hide states of elements
		setAllElementsInitialStates();
		
		//3. Bind change events
		initElementsChangeEvents();
		
		//Test
		//productCalculations();
	/**
	 * 
	 * Set variables values and initial state - END 
	 * 
	 * */
	
});

//Numeric validation on textfield
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}