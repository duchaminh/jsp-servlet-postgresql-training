/*$("#adduser").validate({
        rules: {
            firstName: {
            	required: true,
            	maxlength:10
            },
            familyName: {
            	required: true,
            	maxlength:10
            },
            userId:{
            	required: true,
            	maxlength:8
            },
            password:{
            	required: true,
            	maxlength:8
            },
            age:{
            	number:true,
            	min:0,
            	max:60
            }
        },
        messages: {
            firstName: {
            	required: " 名が入力されていません。",
            	maxlength: "maxLength is 10 characters"
            },
            familyName: {
            	required: "姓が入力されていません。",
            	maxlength: "maxLength is 10 characters"
            },
            userId: {
            	required: "ユーザーIDが入力されていません。",
            	maxlength: "maxLength is 8 characters"
            },
            password: {
            	required: "パスワードが入力されていません。",
            	maxlength: "maxLength is 8 characters"
            },
            age: "please enter a valid age"
        },
        submitHandler: function (form) {
            $("#myModal").modal('show');
    		$('#SubForm').click(function () {
                form.submit();
           });
        },
        highlight: function(element) {
            $(element).css('background', '#ffdddd');
        },
        
        // Called when the element is valid:
        unhighlight: function(element) {
            $(element).css('background', '#ffffff');
        }
    });
var admin = $("#admin").val();
if(admin == 1)
	$('#admin').prop('checked', true);*/