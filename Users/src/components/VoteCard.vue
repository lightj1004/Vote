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
                            <th scope="col">投票</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="element in elements" :key="element.id">
                            <th scope="row">{{ element.id }}</th>
                            <td>{{ element.name }}</td>
                            <td>{{ getCounts(element.id) }}</td>
                            <td></td>
                            </tr>
                        </tbody>
                    </table>
                    
            </div>
        </div>
</template>
    
<script setup>
const props = defineProps(["elements","counts"]);
const emits = defineEmits(["showUpdate"]);

const getCounts = function(id){
    return props.counts[id-1].cnt;
}
    
</script>
    
<style>
    
</style>