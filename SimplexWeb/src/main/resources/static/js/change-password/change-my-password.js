/**
 * http://usejsdoc.org/
 */
$(function() {
	$('.changemyPasswordBtn').on('click', function(e){
		e.preventDefault();
		var $change_my_password_form = $('#changeMyPasswordForm');
		var currentPwd = $change_my_password_form.find("[name='currentPassword']").val();
		var newPwd = $change_my_password_form.find("[name='newPassword']").val();
		var confirmPwd = $change_my_password_form.find("[name='confirmPassword']").val();
		
		console.log('currentPwd : ' + currentPwd);
		console.log('newPwd : ' + newPwd);
		console.log('confirmPwd : ' + confirmPwd);
		
	});

});