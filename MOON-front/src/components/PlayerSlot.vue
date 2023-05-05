<script setup>
import { popup, show, auth,player,characters,resetPlayer } from '../main';
import { usePlayers } from "../assets/game/players"
import path from "../assets/path_data.json"

import BinVue from "../components/icon/Bin.vue";
import PasswordModal from '../components/authentication/PasswordModal.vue';
import ErrorAlert from '../components/ErrorAlert.vue';
import { ref, onMounted, computed } from 'vue';
import router from "../router/index.js";

const myPlayers = usePlayers()
onMounted(async () => {
    await myPlayers.fetchPlayers()
})

const searchKeyword = ref('')
const filterPlayers = computed(() => {
    return myPlayers.getPlayers().filter((player) =>
        player.name?.toLowerCase().includes(searchKeyword.value.toLowerCase())
    ).sort((a, b) => b.level - a.level)
})

const selectedPlayer = ref('')
const selectedModal = ref('')

const enterAccount = (account) => {
    if (!checkPassword(account)) return
    resetPlayer()
    for (const key in account) {
        if (key === "coin") player.value.coin.coin = account.coin
        else if (key === "character") continue
        else player.value[key] = account[key]
    }
    auth.value = true
    if (account.health <= 0)  player.value.health = account.maxHealth
    
    player.value.maxHealth = account.maxHealth
    player.value.character = characters?.[account.character]
    router.push({ name: 'Camp' })
}

const removeAccount = (account) => {
    if (!checkPassword(account)) return
    myPlayers.removePlayer(account.id)
}
const checkPassword = (account) => {
    if (!account) {
        popup("wrongPassword", 3000)
        return false
    }
    return true
}
const setModal = (modalId) => selectedModal.value = modalId
const setPlayer = (player) => selectedPlayer.value = player
</script>
 
<template>
    <div class="w-screen h-screen bg-cover screen" :style="`background-image: url('${path.mainimages}');`">
        <div class="flex flex-col items-center w-full h-full justify-center'">
            <ErrorAlert v-show="show.wrongPassword" error-text="Wrong Password!" />
            <div class="w-full h-full flex flex-col items-center justify-center space-y-4">
                <div class="justify-center items-center flex w-full h-1/6 text-center ">
                    <h1 class="text-5xl  text-slate-900">Choose your account</h1>
                </div>
                <div class="w-3/4">
                    <input type="text" placeholder="search name..."
                        class="text-center w-full py-1 text-2xl bg-white text-slate-900 border-4 border-slate-700"
                        v-model.trim="searchKeyword">
                </div>
                <div class="w-3/4 h-full bg-slate-900 space-y-2 p-2 overflow-hidden flex flex-col">
                    <router-link to="/character-selector">
                        <div class="p-2 w-full text-center bg-emerald-500 hover:bg-teal-600">

                            <button class="text-3xl text-white" @click="resetPlayer()">create account...</button>

                        </div>
                    </router-link>
                    <div class="overflow-scroll flex-1 h-full space-y-2">
                        <div class="p-2 text-2xl w-full bg-slate-600 hover:bg-emerald-400 relative "
                            v-for="player of filterPlayers" @click="setPlayer(player)">
                            <label for="loginModal" class="cursor-pointer" @click="setModal('loginModal')">
                                <div class="flex gap-x-4">
                                    <img :src="characters[player.character].icon" alt="" class="w-24 h-24">
                                    <div class="flex-row text-slate-200 w-full">
                                        <p class="">HP {{ player.maxHealth }}</p>
                                        <p class="">LUCK {{ player.luck }}</p>
                                        <p class="">CRIT {{ player.crit }}</p>
                                    </div>
                                </div>
                                <div class="flex flex-wrap">
                                    <h1 class="text-teal-100">{{ player.name }}</h1>&nbsp;
                                    <p class="">LEVEL {{ player.level }}</p>&nbsp;
                                    <p class="text-amber-300">COIN {{ player.coin }}</p>
                                </div>
                            </label>
                            <label for="removeModal" class="cursor-pointer" @click="setModal('removeModal')">
                                <BinVue class="absolute top-3 right-3 text-white hover:text-red-500" />
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <PasswordModal :modal-id="selectedModal" :player="selectedPlayer" @login="selectedModal === 'removeModal' ? removeAccount($event) : enterAccount($event)" />
        </div>
    </div>
</template>

<style scoped>
::-webkit-scrollbar {
    display: none;
}
</style>