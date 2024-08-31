<template>
    <div class="card">
            <div class="card-header">
                <h2>投票項目</h2>
            </div>
            <div class="card-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                            <th scope="col">編號</th>
                            <th scope="col">名稱</th>
                            <th scope="col">票數</th>
                            <th v-if="voted==false" scope="col"><button class="btn btn-primary" @click="doVote">投票</button></th>
                            <th v-if="voted==true" scope="col">已投票</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="element in elements" :key="element.id">
                            <th scope="row">{{ element.id }}</th>
                            <td>{{ element.name }}</td>
                            <td>{{ getCounts(element.id) }}</td>
                            <td><input v-if="voted==false" class="form-check-input" type="checkbox" :value="element.id" @change="doChange(`${element.id}`)"></td>
                            </tr>
                        </tbody>
                    </table>
                    
            </div>
        </div>
</template>
    
<script setup>
import axiosapi from '@/plugins/axios';
import Swal from 'sweetalert2';

const props = defineProps(["elements","counts","voted"]);
const emits = defineEmits(["showUpdate"]);
let voteElement = [];

//Sweetalert2
const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.onmouseenter = Swal.stopTimer;
                toast.onmouseleave = Swal.resumeTimer;
            }
            });

const getCounts = function(id){
    return props.counts[id-1].cnt;
}

function doChange(key){
    if(voteElement.includes(key)){
        voteElement.splice(voteElement.indexOf(key),1);
    }else{
        voteElement.push(key);
    }
}

function doVote(){
    Swal.fire({
        text: "處理中...",
        allowEscapeKey: false,
        allowOutsideClick: false,
        showConfirmButton: false
    })
    axiosapi.post(`/record/vote/${sessionStorage.getItem("user")}`,voteElement).then(function(response){
        if(response.data.success){
            Toast.fire({
						icon: "success",
						title: response.data.message,
					}).then(function(){
                        location.reload();
                    })
        }else{
            Swal.fire({
                icon: 'warning',
                text:response.data.message
            })
        }
    }).catch(function(error){
        Swal.fire({
            icon:'error',
            text:"請檢查連線"
        })
    })
}
    
</script>
    
<style>
    
</style>