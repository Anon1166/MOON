const API_HOST = import.meta.env.VITE_ROOT_API + "/api/announcements"
const API_CATEGORY = import.meta.env.VITE_ROOT_API + "/api/categories"
import router from '../../src/router/index';

async function getAnnounment() {
    try {
        const res = await fetch(`${API_HOST}`)
        if (res.ok) {
            const announment = res.json()
            return announment
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        console.log(err)
    }
}

async function getMode(mode="active", size=5, page=0, category=0 ) {
    try {
        const res = await fetch(`${API_HOST}/pages?mode=${mode}&page=${page}&size=${size}&category=${category}`)
        if (res.ok) {
            const announment = res.json()
            return announment
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        console.log(err)
    }
}

async function getCategories() {
    try {
        const res = await fetch(`${API_CATEGORY}`)
        if (res.ok) {
            const categories = res.json()
            return categories
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        console.log(err)
    }
}

async function getCategoriesById(id) {
    try {
        const res = await fetch(`${API_CATEGORY}/${id}`)
        if (res.ok) {
            const categories = res.json()
            return categories
        }
        else throw new Error('Error, data is error!')
    } catch (error) {
        console.log(err)
    }
}

async function getAnnounmentById(id , count=false) {
    try {
        const res = await fetch(`${API_HOST}/${id}?count=${count}`)
        const announment = await res.json()
        if (res.ok) {
            return announment
        }
        else {
        alert(announment.message);
        count ? router.push({ name: 'User' }) : router.push({ name: 'Home' }) 
        }
    } catch (error) {
        console.log(err)
    }
}

async function cerateAnnouncement(announcement) {
    try {
        const res = await fetch(`${API_HOST}`, {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(announcement)
        })
        const announment = await res.json()
        if (res.ok) return announment
        else {
            const  arr = announment.detail
            arr.forEach(element => {
                alert(element.field +  ": " + element.errorMessage);
            }); 
        }
    } catch (err) {
        console.log(err)
    }
}

async function updateAnnouncement(announcement, id){
    try {
        const res = await fetch(`${API_HOST}/${id}`, {
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(announcement)
        })
        const announment = await res.json()
        if (res.ok) {}
        else {
            const  arr = announment.detail
            arr.forEach(element => {
                alert(element.field +  ": " + element.errorMessage);
            }); 
        }
    } catch (err) {
        console.log(err)
    }
}

async function deleteAnnoumcement(announcement){
    try {
        const res = await fetch(`${API_HOST}/${announcement}`, {
            method: 'DELETE'
        })
        const announment = await res.json()
        if (res.ok) {
        } 
        else {
        alert(announment.message);
        router.push({ name: 'Home' })
        }
    } catch (err) {
        }
} 


export { getAnnounment, getAnnounmentById, cerateAnnouncement, updateAnnouncement, deleteAnnoumcement, getCategories, getCategoriesById , getMode}
