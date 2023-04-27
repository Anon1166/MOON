const API_HOST = "http://25.40.139.90:5000/Announcement"

async function getAnnounment() {
    try {
        const res = await fetch(`${API_HOST}`)
        if (res.ok) {
            const announment = res.json()
            return announment
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        console.log(error)
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
        console.log(error)
    }
}

export { getAnnounment, getAnnounmentById }