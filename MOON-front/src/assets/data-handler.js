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

export { getAnnounment, getAnnounmentById }