<template>

    <!-- Modal -->
    <div ref="exampleRef" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">投票項目</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                        <table>
                        <tr>
                            <td>項目名稱 : </td>
                            <td><input type="text" name="name" :value="element.name" @input="doinput('name', $event)"></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" v-if="isInsert" @click="emits('doInsert',newElement)">新增</button>
                    <button type="button" class="btn btn-primary" v-if="!isInsert" @click="emits('doUpdate',newElement)">修改</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    const props = defineProps(["isInsert", "element"]);
    const emits = defineEmits(["update:element", "doInsert","doUpdate"]);
    const newElement = ref({});
    import bootstrap from 'bootstrap/dist/js/bootstrap.bundle.min.js'
    import { ref,onMounted } from 'vue'
    const exampleRef = ref(null);
    const exampleModal = ref(null);
    function doinput(key, event){
        newElement.value ={
            ...props.element,
            ...newElement.value,
            [key]: event.target.value
        };
    }
    function initialElement(){
        newElement.value ={}
    }
    onMounted(function(){
            exampleModal.value = new bootstrap.Modal(exampleRef.value);
    })

    function showModal(){
            exampleModal.value.show();
    }
    function hideModal(){
            exampleModal.value.hide();
    }

    defineExpose({
            showModal,hideModal,initialElement
    })
</script>

<style>

</style>