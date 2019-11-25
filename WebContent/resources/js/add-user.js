$("#adduser").validate({
        rules: {
            firstName: "required",
            familyName: "required",
            userId:"required",
            password:"required",
            age:{
            	number:true,
            	min:0,
            	max:60
            }
        },
        messages: {
            firstName: "Please enter your firstname",
            familyName: "Please enter famaly name",
            userId: "Please enter your user id",
            password: "please enter your password",
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
	$('#admin').prop('checked', true);