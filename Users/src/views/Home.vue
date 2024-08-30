<template>
    <br><div class="row">
        <VoteCard v-model:elements="elements"></VoteCard>
    </div>
</template>
    
<script setup>
    import VoteCard from '@/components/VoteCard.vue';
    import axiosapi from '@/plugins/axios';
    import Swal from 'sweetalert2';
    import { onMounted } from 'vue';
    import { ref } from 'vue';

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

    //顯示所有投票項目
    function callFind(){
        Swal.fire({
            text: "處理中...",
            allowEscapeKey: false,
            allowOutsideClick: false,
            showConfirmButton: false
        })
        axiosapi.get("/element/").then(function(response){
            Swal.close();
            if(response.data.success){
                elements.value = response.data.elements;
            }else{
                Toast.fire({
                    icon:'info',
                    text: "暫無投票項目"
                })
            }
        }).catch(function(error){
            Swal.fire({
                icon:'warning',
                text: "請檢查連線"
            })
        })
    }

</script>
    
<style>
    
</style>