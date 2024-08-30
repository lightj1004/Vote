<template>
    <br><br><br>
	<div class="col-8 container border border-1 rounded-4">
		<div class="row justify-content-center">
			<div class="col-10">
				<br>
				<h1 style="font-style: italic;text-align: center;">註冊</h1>
				<form class="row g-3">
					<div class="form-floating mb-3 col-6">
						<input type="text" class="form-control" id="floatingInput" aria-describedby="帳號"
							v-model="username" placeholder="帳號" @blur="checkUsername">
						<label for="floatingInput">帳號 <font-awesome-icon icon='fa-solid fa-circle-check'
								style='color: #03c700;display: none;' id="checkUsernameSuccess" /></label>
						<span id="checkUsernameResult"></span>
					</div>
					<div class="form-floating mb-3 col-6">
						<input type="text" class="form-control" id="floatingName" v-model="name" placeholder="姓名">
						<label for="floatingName">姓名</label>
					</div>
					<div class="form-floating mb-3 col-6">
						<input type="password" class="form-control" id="floatingPassword" v-model="password"
							placeholder="密碼" @input="checkPasswordSame">
						<label for="floatingPassword">密碼</label>
					</div>
					<div class="form-floating mb-3 col-6">
						<input type="password" class="form-control" id="floatingcheckPassword" v-model="checkPassword"
							placeholder="確認密碼" @input="checkPasswordSame">
						<label for="floatingcheckPassword">確認密碼
							<font-awesome-icon icon='fa-solid fa-circle-check' style='color: #03c700;display: none;'
								id="checkPasswordSuccess" />
							<font-awesome-icon icon='fa-solid fa-circle-xmark' style='color: #f60404;display: none;'
								id="checkPasswordFalse" />
						</label>
					</div>
					<!-- <Verify @validate="checkValidate"></Verify> -->
					<div class="d-flex justify-content-center gap-2">
						<button type="button" class="btn btn-dark mb-2" @click="doregister">註冊</button>
					</div>
				</form>
				<br>
			</div>
		</div>
	</div>
</template>
    
<script setup>
    import axiosapi from '@/plugins/axios';
    import Swal from 'sweetalert2';
    import { ref,onBeforeMount } from 'vue';
    
    const username = ref();
    const name = ref();
    const password = ref();
    const checkPassword = ref();
    var checkPasswordResult = false;

    //Sweetalert
    const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 1500,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
        }
    });

    //檢查是否已有登入
    onBeforeMount(
        function () {
            if (sessionStorage.getItem("user")) {
                router.push("/");
            }
        }
    )

    //檢查密碼與確認密碼是否相同
    function checkPasswordSame() {
        if (password.value != null && password.value != '' && checkPassword.value != null && checkPassword.value != '') {
            if (password.value === checkPassword.value) {
                checkPasswordResult = true;
                document.getElementById("checkPasswordSuccess").style.display = "inline";
                document.getElementById("checkPasswordFalse").style.display = "none";
            } else {
                checkPasswordResult = false;
                document.getElementById("checkPasswordSuccess").style.display = "none";
                document.getElementById("checkPasswordFalse").style.display = "inline";
            }
        } else if (password.value == null || password.value == '' || checkPassword.value != null || checkPassword.value != '') {
            checkPasswordResult = false;
            document.getElementById("checkPasswordSuccess").style.display = "none";
            document.getElementById("checkPasswordFalse").style.display = "none";
        }
    }

    //進行註冊
    function doregister(){
        Swal.fire({
            text: "處理中...",
            allowEscapeKey: false,
            allowOutsideClick: false,
            showConfirmButton: false
        })
        if(checkPasswordResult == true){
            let request = {
                "username": username.value,
                "name": name.value,
                "password": password.value,
            }

            axiosapi.post("/users/",request).then(function(response){
                if (response.data.success) {
					Toast.fire({
						icon: "success",
						title: response.data.message,
					})
                }else{
                    Swal.fire({
                        icon:'error',
                        text: response.data.message
                    })
                }
            }).catch(function(error){
                Swal.fire({
                    icon: 'warning',
                    text: "請檢查連線"
                })
            })
        }else{
            Swal.fire({
                icon: 'warning',
                text: "密碼與確認密碼不相同"
            })
        }
        
    }
</script>
    
<style>
    
</style>