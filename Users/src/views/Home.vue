<template>
    <br><div class="row">
        <VoteCard v-model:elements="elements" v-model:counts="counts" v-model:voted="voted"></VoteCard>
    </div>
</template>
    
<script setup>
    import VoteCard from '@/components/VoteCard.vue';
    import axiosapi from '@/plugins/axios';
    import Swal from 'sweetalert2';
    import { onBeforeMount } from 'vue';
    import { onMounted } from 'vue';
    import { ref } from 'vue';
import { useRouter } from 'vue-router';

    //Card
    const elements = ref();
    const counts = ref();
    const voted = ref(false);

    //Router
    const router = useRouter();

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

    onBeforeMount(function(){
        if(!sessionStorage.getItem("user")){
            router.push("/login");
        }
    })

    
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
            
            if(response.data.success){
                elements.value = response.data.elements;
                axiosapi.get("/record/").then(function(response){
                    if(response.data.success){
                        Swal.close();
                        counts.value = response.data.counts;
                    }
                    
                }).catch(function(error){
                    Swal.fire({
                        icon:'warning',
                        text:"票數查詢失敗"
                    })
                })

                axiosapi.get(`/record/check/${sessionStorage.getItem("user")}`).then(function(response){
                    if(response.data.success){
                        voted.value = true;
                    }else{
                        voted.value = false;
                    }
                }).catch(function(error){
                    Swal.fire({
                        icon:'warning',
                        text:"投票紀錄異常"
                    })
                })
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