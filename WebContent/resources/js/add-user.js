$("#adduser").validate({
        rules: {
            firstName: "required",
            familyName: "required",
            userId:"required",
            password:"required"
        },
        messages: {
            firstName: "Please enter your firstname",
            familyName: "Please enter fimaly name",
            userId: "Please enter your user id",
            password: "please enter your password"
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