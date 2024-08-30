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
							id="floatingInput" aria-describedby="帳號" v-model="username" placeholder="帳號" @input="doinput">
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
    
</script>
    
<style>
    
</style>