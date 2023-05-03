const API_HOST = import.meta.env.VITE_BASE_URL

async function getAnnounment() {
    try {
        const res = await fetch(`${API_HOST}`)
        if (res.ok) {
            const announment = res.json()
            return announment
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        
    }
}

async function getAnnounmentById(id) {
    try {
        const res = await fetch(`${API_HOST}/${id}`)
        if (res.ok) {
            const announment = res.json()
            return announment
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        
    }
}

async function cerateAnnouncement(announcement) {
    try {
        const res = await fetch(`${API_PLAYERS}`, {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(announcement)
        })
        if (res.status === 201) return res.json()
        else throw new Error('cannot add!')
    } catch (err) {
        console.log(err)
    }
}

async function updateAnnouncement(announcement){
    try {
        const res = await fetch(`${API_PLAYERS}/${playerData.id}`, {
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(announcement)
        })
        if (res.ok) {}
        // console.log("Update Successfully")
        else throw new Error('cannot add!')
    } catch (err) {
        console.log(err)
    }
}

async function deleteAnnoumcement(announcement){
    try {
        const res = await fetch(`${API_PLAYERS}/${announcement}`, {
            method: 'DELETE'
        })
        if (res.ok) {
            // console.log("Delete Successfully")
        } else throw new Error('cannot delete!')
    } catch (err) {
        console.log(err)
    }
} 


export { getAnnounment, getAnnounmentById, cerateAnnouncement, updateAnnouncement, deleteAnnoumcement}