$("#adduser").validate({
        rules: {
            firstName: "required",
            familyName: "required",
        },
        messages: {
            firstName: "Please enter your firstname",
            familyName: "Please enter your fimaly name",
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