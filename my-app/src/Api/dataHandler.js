import api from './apis.json'

export let dataHandler = {
	// get all data from DB tables
	getAppUsers: async function () {
		return await apiGet(api.getAllAppUsers);
	},

	getAnimals: async function () {
		return await apiGet(api.getAllAnimals);
	},
	getAnimalTypes: async function () {
		return await apiGet(api.getAllAnimalTypes);
	},
	getBreeds: async function () {
		return await apiGet(api.getAllBreeds);
	},
	addNewUser: async function (data) {
	return await apiPost(api.newAppUsers, data);
	},
}


// funkcje asynchroniczne to powtórka z JS
async function apiGet(url) {
	let response = await fetch(url, {
		method: "GET",
	});
	if (response.ok) {
		return await response.json();
	}
}

async function apiPost(url, payload) {
	let response = await fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(payload),
	});
	if (response.ok) {
		return await response.json();
	}
}