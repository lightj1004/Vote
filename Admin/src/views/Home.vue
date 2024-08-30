<template>
    <br><div class="row">
        <div class="col-4">
            <button class="btn btn-primary" @click="showInsert">新增投票項目</button>
            
        </div>
    </div><br>
    <div class="row">
        <VoteCard v-for="element in elements" :key="element.id" :element="element" @show-update="showUpdate"></VoteCard>
    </div>
    <VoteModal ref="voteModal" v-model:element="element" :is-insert="isInsert" @do-insert="callCreate" @do-update="callModify"></VoteModal>
</template>
    
<script setup>
    import VoteCard from '@/components/VoteCard.vue';
import VoteModal from '@/components/VoteModal.vue';
import axiosapi from '@/plugins/axios';
import Swal from 'sweetalert2';
import { onMounted } from 'vue';
    import { ref } from 'vue';

    // Modal
    const isInsert = ref(false);
    const element = ref({});
    const voteModal = ref(null);

    //Card
    const elements = ref();

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

    
    onMounted(function(){
        callFind();
    })

    //開啟新增Modal
    function showInsert(){
        isInsert.value = true;
        element.value = {};
        voteModal.value.initialElement();
        voteModal.value.showModal();
    }

    //開啟修改Modal
    function showUpdate(oldElement){
        isInsert.value = false;
        voteModal.value.initialElement();
        element.value = oldElement;
        voteModal.value.showModal();
    }

    //顯示所有投票項目
    function callFind(){
        Swal.fire({
            text: "處理中...",
            allowEscapeKey: false,
            allowOutsideClick: false,
            showConfirmButton: false
        })
        axiosapi.get("/api/element/").then(function(response){
            Swal.close();
            if(response.data.success){
                elements.value = response.data.elements;
            }else{
                Toast.fire({
                    icon:'info',
                    text: "請先新增一項投票項目"
                })
            }
        }).catch(function(error){
            Swal.fire({
                icon:'warning',
                text: "請檢查連線"
            })
        })
    }

    //新增投票項目
    function callCreate(newElement){
        Swal.fire({
            text: "處理中...",
            allowEscapeKey: false,
            allowOutsideClick: false,
            showConfirmButton: false
        })

        axiosapi.post("/api/element/",newElement).then(function(response){
            if(response.data.success){
                voteModal.value.hideModal();
                Toast.fire({
                    icon: "success",
                    title: "新增成功",
                })
                .then(function(){
                    callFind();
                })
            }else{
                Swal.fire({
                    text:"錯誤 : "+ response.data.message,
                    icon:"warning"
                })
            }
        }).catch(function(error){
            console.log("error",error);
            Swal.fire({
                text:"新增失敗 : "+ error.message,
                icon:"error"
            })
        });
    }

    //修改投票項目
    function callModify(newElement){
        if(newElement.id != null){
            Swal.fire({
                text: "處理中...",
                allowEscapeKey: false,
                allowOutsideClick: false,
                showConfirmButton: false
            })

            axiosapi.put(`/api/element/${newElement.id}`,newElement).then(function(response){
                if(response.data.success){
                    voteModal.value.hideModal();
                    Toast.fire({
                        icon: "success",
                        title: "修改成功",
                    })
                    .then(function(){
                        callFind();
                    })
                }else{
                    Swal.fire({
                        text:"錯誤 : "+ response.data.message,
                        icon:"warning"
                    })
                }
            }).catch(function(error){
                console.log("error",error);
                Swal.fire({
                    text:"修改失敗 : "+ error.message,
                    icon:"error"
                })
            });
            }
    }

</script>
    
<style>
    
</style>