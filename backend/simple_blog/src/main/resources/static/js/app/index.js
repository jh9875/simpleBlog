var main = {
    init : function () {
        var _this = this;
        $('#btn-posts-save').on('click', function () {
            _this.postsSave();
        });

        $('#btn-posts-update').on('click', function () {
            _this.postsUpdate();
        });

        $('#btn-posts-delete').on('click', function () {
            _this.postsDelete();
		});
		
		$('#btn-user-update').on('click', function () {
			_this.userUpdate();
		});
		
		$('#btn-user-delete').on('click', function () {
			_this.userDelete();
		});

		$('#btn-user-register').on('click', function () {
			_this.userRegister();
		});
		

		//test
		$('#btnUpload').on('click', function() {
			_this.upload();
		})
    },
    postsSave : function () {
        var data = {
            title: $('#title').val(),
			content: $('#content').val(),
			email: $('#email').val(),
			image: $('#image').val()
		};
		var author = $('#author').val();

		//수정 필요
		// var form =$('#form')[0];
		// var formData =new FormData(form);
		// console.log(form);
		// console.log(formData);
		// console.log(typeof(form));
		
		// var image =$('#image').val();
		// var aa =$('#email').val();
		// alert(typeof(image));
		// alert(typeof(aa));
		// console.log(image);
		// alert(image);
		

        $.ajax({
            type: 'POST',
            url: '/api/v1/'+author+'/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    postsUpdate : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

		var id = $('#id').val();
		var author = $('#author').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/'+author+'/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    postsDelete : function () {
		var id = $('#id').val();
		var author = $('#author').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/'+author+'/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
	},
	
	userUpdate : function () {
		var data = {
            name: $('#name').val(),
			email: $('#email').val(),
			author: $('#author').val(),
			picture: jQuery("#picture").attr("src")
		};
		
		$.ajax({
            type: 'PUT',
            url: '/api/v1/me/setting',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('유저가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
	},

	// userDelete : function () {
	// 	var email = $('#email').val();

	// 	$.ajax({
    //         type: 'DELETE',
    //         url: '/api/v1/user/setting' +email,
    //         dataType: 'json',
    //         contentType:'application/json; charset=utf-8'
    //     }).done(function() {
    //         alert('유저가 삭제되었습니다.');
    //         window.location.href = '/';
    //     }).fail(function (error) {
    //         alert(JSON.stringify(error));
    //     });
	// }

	userRegister : function () {
		var data = {
            name: $('#name').val(),
			email: $('#email').val(),
			author: $('#author').val(),
			picture: jQuery("#picture").attr("src")
		};
		
		$.ajax({
            type: 'PUT',
            url: '/api/v1/me/register',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('유저가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
	},

	//수정 필요.
	upload : function (event) {
		event.preventDefault();

		var form = $('#uploadForm')[0]
		var formData = new FormData(form);

		// console.log(form);
		console.log(data);
    
		$('#btnUpload').prop('disabled', true);
	
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/v1/upload",
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$('#btnUpload').prop('disabled', false);
        	alert('success')
        },
        error: function (e) {
			console.log("ERROE: ", e);
            $('#btnUpload').prop('disabled', false);
            alert('fail');
        }
    });
	}
};

main.init();