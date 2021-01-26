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
			email: $('#email').val()
		};
		var author = $('#author').val();
		var form =$('#form')[0];
		var formData = new FormData(form);
		formData.append('file', $('#file'));
		formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));
		
		$.ajax({
            type: 'POST',
            url: '/api/v1/'+author+'/posts',
            processData: false,
            contentType:false,
            data: formData,
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
		var formData = new FormData(form);
		formData.append('file', $('#file'));
		formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

        $.ajax({
            type: 'PUT',
            url: '/api/v1/'+author+'/posts/'+id,
            processData: false,
            contentType:false,
            data: formData,
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
	}
};

main.init();