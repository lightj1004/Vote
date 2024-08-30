<template>
<br><br><br>
    <div class="col-8 container border border-1 rounded-4">
		<div class="row justify-content-center">
			<div class="col-10">
                <br>
			<h1 style="font-style: italic; text-align: center;">登入</h1>
				<form>
					<div class="form-floating mb-3">
						<input type="text" class="form-control"
							id="floatingInput" aria-describedby="帳號" v-model="username" placeholder="帳號">
                        <label for="floatingInput">帳號</label> 
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control"
							id="floatingPassword" v-model="password" placeholder="密碼">
                        <label for="floatingPassword">密碼</label>
					</div>
                    <br>
                    <div class="d-flex justify-content-center gap-2">
                        
                        <button type="button" class="btn btn-dark mb-2" @click="dologin">送出</button>
                        <!-- &nbsp; -->
                        <RouterLink type="button" class="btn btn-dark mb-2" to="/register">註冊</RouterLink>
                    </div>
				</form>
                <br>
			</div>
		</div>
	</div>
</template>
    
<script setup>
	import { useRouter } from 'vue-router';
    import { ref,onBeforeMount } from 'vue';
	import axiosapi from '@/plugins/axios';
	import Swal from 'sweetalert2';

	const router = useRouter();

	//帳號密碼
	const username = ref();
	const password = ref();

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

	//檢查是否已經登入
	onBeforeMount(function(){
		if(sessionStorage.getItem("user")){
			router.push("/");
		}
	})

	//登入
	function dologin(){
		Swal.fire({
            text: "處理中...",
            allowEscapeKey: false,
            allowOutsideClick: false,
            showConfirmButton: false
        })
		let request = {
			"username":username.value,
			"password":password.value
		}

		axiosapi.post("/users/login",request).then(function(response){
				if (response.data.success) {
					Toast.fire({
						icon: "success",
						title: "歡迎,"+response.data.user,
					}).then(function(){
						sessionStorage.setItem("user",response.data.user);
						location.reload();
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
                    text: "請檢查連線:"+error.message
                })
			})
	}
</script>
    
<style>
    
</style>