
// app.js

const API_BASE = "http://localhost:8080/api";

document.addEventListener("DOMContentLoaded", () => {
    fetchDeals();
});

async function fetchDeals(city = "") {
    let url = `${API_BASE}/deals`;
    if (city) {
        url += `?city=${encodeURIComponent(city)}`;
    }
    const res = await fetch(url);
    const deals = await res.json();
    renderDeals(deals);
    populateCities(deals);
}

function renderDeals(deals) {
    const list = document.getElementById("deals-list");
    if (!list) return;
    list.innerHTML = "";
    if (deals.length === 0) {
        list.innerHTML = "<p>No deals found.</p>";
        return;
    }
    deals.forEach(deal => {
        const card = document.createElement("div");
        card.className = "deal-card";
        card.innerHTML = `
            <h3>${deal.title}</h3>
            <p><strong>Restaurant:</strong> ${deal.restaurantName}</p>
            <p><strong>City:</strong> ${deal.city}</p>
            <p><strong>Description:</strong> ${deal.description}</p>
            <p><strong>Valid:</strong> ${deal.validFrom} - ${deal.validTo}</p>
        `;
        list.appendChild(card);
    });
}

function populateCities(deals) {
    const select = document.getElementById("city-select");
    if (!select) return;
    // Gets unique cities from deals
    const cities = Array.from(new Set(deals.map(d => d.city))).sort();
    // Clears existing options except "All Cities"
    select.innerHTML = '<option value="">All Cities</option>';
    cities.forEach(city => {
        const opt = document.createElement("option");
        opt.value = city;
        opt.textContent = city;
        select.appendChild(opt);
    });
    select.onchange = () => {
        fetchDeals(select.value);
    };
}
