/**
 * Simplex Custom JS
 */

$(document).ready(function(){
	//Set navigation menu selected
	setNavigation();
	
	//Slimscroll plugin for scrollbar
	/*$("body").slimScroll({
        size: '8px', 
        width: '100%', 
        height: '100%', 
        color: '#ff8d3f', 
        allowPageScroll: true, 
        alwaysVisible: true  ,
        railVisible: true,
        railColor: '#ffffff',
      });*/
	
	// Actual capitalize first letter
	$('.text-capitalize').on('blur', function() {
		try{
			var text = $(this).val();
			var textarray = text.split(' ');
			var finalText = '';
			for(var i=0; i<textarray.length; i++){
				finalText += textarray[i].substr(0,1).toUpperCase() + textarray[i].substr(1) + ' ';
			}
			$(this).val(finalText.trim());
		}catch (e) {
			console.log(e);
		}
	});
	
	$('.text-no-space').on('keyup', function() {
		try{
			$(this).val($(this).val().trim());
		}catch (e) {
			console.log(e);
		}
	});
	
	//Date Picker
	$('.date-group .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true,
        format: 'dd-M-yyyy'
    }).on('show.bs.modal', function(event) {
        // prevent datepicker from firing bootstrap modal "show.bs.modal"
        event.stopPropagation(); 
    }).on('hide.bs.modal', function(event) {
        // prevent datepicker from firing bootstrap modal "show.bs.modal"
        event.stopPropagation(); 
    });;
	
	//Chosen Select Autocomplete
	/*$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});*/
	
	//Remove spaces between words
	$('.trim').on('input propertychange paste', function() {
	    $(this).val(this.value.replace(/\s/g, ""));
	});
	
	//Allow alphabets only
	$('.alphabets-only').on('focusin', function(){
	    $(this).data('val', $(this).val());
	}).on('input propertychange paste', function(e) {
		var prevVal = $(this).data('val');
		var newVal = $(this).val();
		if(/^[a-zA-Z- ]*$/.test(newVal) == false) {
			$(this).val(prevVal);
		    e.preventDefault();
		}
	});
	
	//Allow alphabets and numbers only
	$('.alphanumerics-only').on('focusin', function(){
	    $(this).data('val', $(this).val());
	}).on('input propertychange paste', function(e) {
		var prevVal = $(this).data('val');
		var newVal = $(this).val();
		if(/^[a-zA-Z0-9- ]*$/.test(newVal) == false) {
			$(this).val(prevVal);
		    e.preventDefault();
		}
	});
	
	//To add asterisk to required field
	/*$("[required]").each(function(){
		$(this).parent( "label" ).after( "<span style='color:red;'>*</span>" );
	});*/
	
	//Modal reset settings
	$('.modal.reset-on-hide').on('hide.bs.modal', function () {
		if($(this).data('reset-on-close')){
			$(this).find('form').each(function(){	//Reset all forms in the modal
				$(this).get(0).reset();
			});
		}
   	});

});

function showloader() {
	$('.loader-overlay').show();
}

function hideloader() {
	$('.loader-overlay').hide();
}


/*Set sidebar menu selected*/
function setNavigation() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);
    
    $(".nav a").each(function () {
        var href = $(this).attr('href');
        if (path.substring(0, href.length) === href) {
            $(this).closest('li').addClass('active');
            var $ulEle =  $(this).closest('ul');
            $ulEle.addClass('in');
            $ulEle.closest('li').addClass('active');
        }
    });
}
	
	
/*Default options for toastr*/
const TOASTER_OPTIONS = {
		  "closeButton": true,
		  "debug": false,
		  "progressBar": true,
		  "preventDuplicates": false,
		  "positionClass": "toast-top-center",
		  "onclick": null,
		  "showDuration": "400",
		  "hideDuration": "1000",
		  "timeOut": "7000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		};

/*Setup Modal According To Modal Type*/
function setUpModelAccordingToType(MODAL_TYPE, $modalElement
								, $viewForm, DATA_MAP
								, MODAL_TITLE_NEW, MODAL_SUBTITLE_NEW
								, MODAL_TITLE_UPDATE, MODAL_SUBTITLE_UPDATE){
	//Other modal stuff
	$modalElement.data('modal-type', MODAL_TYPE);					//Set modal type
	
	if(MODAL_TYPE == 'NEW'){						//If the modal is for saving new entity
		$modalElement.find('form').each(function(){	//Reset all forms in the modal
			$(this).get(0).reset();
		}); 
		$modalElement.find($('.modal-title')).text(MODAL_TITLE_NEW);		//Set modal title
		$modalElement.find($('.modal-subtitle')).text(MODAL_SUBTITLE_NEW);	//Set modal subtitle
		
	}else if(MODAL_TYPE == 'EDIT'){						//If the modal is for updating an existing entity
		$modalElement.find($('.modal-title')).text(MODAL_TITLE_UPDATE);		//Set modal title
		$modalElement.find($('.modal-subtitle')).text(MODAL_SUBTITLE_UPDATE);	//Set modal subtitle
		buildViewForm($viewForm, DATA_MAP, $modalElement)	//Set values to modal fields
	}
	
	$modalElement.modal('show');	//Modal show
}

/*View Modal Form Builder*/
function buildViewForm($viewForm, DATA_MAP, $modalElement){
	$.each(DATA_MAP, function(key, value){
		try{
			var $el = $viewForm.find("[data-fill='"+key+"']");
//			console.log('buildViewForm > select found $el : ' + $el + ' key : ' + key + ' DATA_MAP[key] : ' + DATA_MAP[key]);
			var elTag = $el.prop('tagName').toLowerCase();
			if(elTag == 'input'){
				var elType = $el.attr('type');
				switch (elType) {
				case 'text':
					$el.val(DATA_MAP[key]);
					break;
				case 'hidden':
					$el.val(DATA_MAP[key]);
					break;
				default:
					break;
				}
			}else if(elTag == 'select'){
				//For jquery chosen plugin
				if($el.hasClass('chosen-select')){
					$el.val(DATA_MAP[key]).trigger('chosen:updated');
				}else{
					$el.val(DATA_MAP[key]);
				}
			}else if(elTag == 'textarea'){
				$el.val(DATA_MAP[key]);
			}
		}catch (e) {
			console.log(e);
		}
	});
}


/*Navigation Path in navbar*/
function buildNavPath(navlist){
	/* var navlist = {'Home':'user/home', 'Template':'#', 'Global Template':'#'}; */
	var $navpath = $('.navpath');
	var href = '#';
	$navpath.html('');
	for(var i=0, keys=Object.keys(navlist), l=keys.length; i<l; i++) {
	    if(i!=l && i!=0){
	    	$navpath.append(' > ');
	    }
	    
	    
	    if(navlist[keys[i]] == '#'){
	    	href = '#';
	    }else{
	    	href = contextRoot + navlist[keys[i]];
	    }
	    
	    
	    if(i == l-1){
	    	$span = $('<b><a style="color:#eb8b3b;" href="' + href + '">' + keys[i] + '</a></b>');
	    	$navpath.append($span);
	    }else{
	    	$span = $('<a href="' + href + '">' + keys[i] + '</a>');
	    	$navpath.append($span);
	    }
	} 
}