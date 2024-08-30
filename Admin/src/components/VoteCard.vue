<template>
    <div class="col-lg-4 col-md-6">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">項目名稱 : {{element.name}}</h5>
                <p class="card-text">項目敘述 : {{ element.description }}</p>
                <p class="card-text">選項一 : {{ element.optionOne }}</p>
                <p class="card-text">選項二 : {{ element.optionTwo }}</p>
                <button class="btn btn-primary" @click="emits('showUpdate',element)">修改</button>
            </div>
        </div>
    </div>
</template>
    
<script setup>

const props = defineProps(["element"]);
const emits = defineEmits(["showUpdate"]);
    
</script>
    
<style>
    
</style>