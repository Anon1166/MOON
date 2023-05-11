import { defineStore, acceptHMRUpdate } from 'pinia'
import { ref } from 'vue'

export const modeStore = defineStore('mode', () => {


    const modes = ref("active")


    const changeModes = () => {

        if (modes.value === "active") {

            modes.value = "close"

        } else {

            modes.value = "active"
        }

    }

    return { modes, changeModes }

})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(modeStore, import.meta.hot))
}



