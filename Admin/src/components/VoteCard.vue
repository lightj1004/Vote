<template>
        <div class="card" style="width: 50rem;">
            <div class="card-header">
                <h2>投票項目</h2>
            </div>
            <div class="card-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                            <th scope="col">編號</th>
                            <th scope="col">名稱</th>
                            <!-- <th scope="col">票數</th> -->
                            <th scope="col">修改</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="element in elements" :key="element.id">
                            <th scope="row">{{ element.id }}</th>
                            <td>{{ element.name }}</td>
                            <!-- <td></td> -->
                            <td><button class="btn btn-primary" @click="emits('showUpdate',element)">修改</button></td>
                            </tr>
                        </tbody>
                    </table>
                    
            </div>
        </div>
</template>
    
<script setup>

const props = defineProps(["elements"]);
const emits = defineEmits(["showUpdate"]);
    
</script>
    
<style>
    
</style>