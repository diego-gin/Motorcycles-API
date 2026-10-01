
let catalog = [];
let authenticated = false;

// DOM references
const authButton = document.getElementById("authButton");
const searchInput = document.getElementById("searchInput");
const sortSelect = document.getElementById("sortSelect");
const catalogList = document.getElementById("catalogList");

const detailsMessage = document.getElementById("detailsMessage");
const detailsImage = document.getElementById("detailsImage");
const detailsName = document.getElementById("detailsName");
const detailsYear = document.getElementById("detailsYear");

const detailsCategory = document.getElementById("detailsCategory");
const detailsCountry = document.getElementById("detailsCountry");
const detailsGeneration = document.getElementById("detailsGeneration");
const detailsVersion = document.getElementById("detailsVersion");

const detailsDisplacement = document.getElementById("detailsDisplacement");
const detailsPower = document.getElementById("detailsPower");
const detailsTorque = document.getElementById("detailsTorque");
const detailsEngineType = document.getElementById("detailsEngineType");
const detailsCylinders = document.getElementById("detailsCylinders");
const detailsCooling = document.getElementById("detailsCooling");

const detailsWeight = document.getElementById("detailsWeight");
const detailsFuelTankCapacity = document.getElementById("detailsFuelTankCapacity");
const detailsSeatHeight = document.getElementById("detailsSeatHeight");
const detailsTopSpeed = document.getElementById("detailsTopSpeed");

// Functions
function formatValue(value, unit = "") {
    if (value == null) {
        return "—";
    }
    return `${value}${unit}`;
}

function getCookie(name) {
    const cookies = document.cookie.split("; ");

    const cookie = cookies.find(item =>
        item.startsWith(`${name}=`)
    );

    if (!cookie) {
        return null;
    }

    return decodeURIComponent(cookie.split("=")[1]);
}

// Auth
async function loadAuthStatus() {
    try {
        const response = await fetch("/api/auth/status");

        if (!response.ok) {
            throw new Error(`Failed to load authentication status: ${response.status}`);
        }

        const status = await response.json();

        authenticated = status.authenticated;

        if (authenticated) {
            authButton.textContent = "Log out";
        } else {
            authButton.textContent = "Log in";
        }
    } catch (error) {
        console.error(error);

        authenticated = false;
        authButton.textContent = "Log in";
    }
}

async function logout() {
    authButton.disabled = true;

    try {
        const csrfToken = getCookie("XSRF-TOKEN");

        const response = await fetch("/logout", {
            method: "POST",
            headers: {
                "X-XSRF-TOKEN": csrfToken
            }
        });

        if (!response.ok) {
            throw new Error(`Failed to logout: ${response.status}`);
        }

        await loadAuthStatus();

    } catch (error) {
        console.error(error);
    } finally {
        authButton.disabled = false;
    }
}

// Catalog
async function loadCatalog() {
    try {
        const response = await fetch("/api/motorcycles");

        if (!response.ok) {
            throw new Error(`Failed to load catalog: ${response.status}`);
        }

        catalog = await response.json();

        const visibleItems = updateCatalogList();

        if (visibleItems.length > 0) {
            void loadDetails(visibleItems[0].id);
        }

    } catch (error) {
        console.error(error);

        catalogList.replaceChildren();

        const message = document.createElement("p");
        message.textContent = "Failed to load catalog";

        catalogList.appendChild(message);
    }
}

function updateCatalogList() {
    const searchTerm = searchInput.value.toLowerCase();

    const filteredItems = catalog.filter(item => {
        return item.brand.toLowerCase().includes(searchTerm)
            || item.model.toLowerCase().includes(searchTerm);
    });

    const sortOption = sortSelect.value;

    if (sortOption === "name-asc") {
        filteredItems.sort((a, b) => {
            const nameA = `${a.brand} ${a.model}`;
            const nameB = `${b.brand} ${b.model}`;

            return nameA.localeCompare(nameB);
        });
    }

    if (sortOption === "name-desc") {
        filteredItems.sort((a, b) => {
            const nameA = `${a.brand} ${a.model}`;
            const nameB = `${b.brand} ${b.model}`;

            return nameB.localeCompare(nameA);
        });
    }

    if (sortOption === "year-desc") {
        filteredItems.sort((a, b) => b.year - a.year);
    }

    if (sortOption === "year-asc") {
        filteredItems.sort((a, b) => a.year - b.year);
    }

    if (sortOption === "displacement-desc") {
        filteredItems.sort((a, b) => b.displacement - a.displacement);
    }

    if (sortOption === "displacement-asc") {
        filteredItems.sort((a, b) => a.displacement - b.displacement);
    }

    renderCatalog(filteredItems);

    return filteredItems;
}

function renderCatalog(items) {
    catalogList.replaceChildren();

    if (items.length === 0) {
        const message = document.createElement("p");
        message.textContent = "No motorcycles found.";
        catalogList.appendChild(message);
        return;
    }

    items.forEach(item => {
        const element = document.createElement("button");

        element.textContent = `${item.brand} ${item.model}`;

        element.addEventListener("click", () => {
            void loadDetails(item.id);
        });
        catalogList.appendChild(element);
    });
}

// Details
async function loadDetails(id) {
    try {
        detailsMessage.textContent = "";

        const response = await fetch(`/api/motorcycles/${id}`);

        if (!response.ok) {
            throw new Error(`Failed to load details: ${response.status}`);
        }

        const item = await response.json();

        renderDetails(item);

    } catch (error) {
        console.error(error);
        detailsMessage.textContent = "Failed to load motorcycle details";
    }
}

function renderDetails(item) {

    if (item.imageUrl) {
        detailsImage.src = item.imageUrl;
        detailsImage.alt = `${item.brand} ${item.model}`;
    } else {
        detailsImage.removeAttribute("src");
        detailsImage.alt = "Image not available";
    }
    detailsName.textContent = `${item.brand} ${item.model}`;
    detailsYear.textContent = `${item.year}`;
    detailsCategory.textContent = formatValue(item.category);
    detailsCountry.textContent = formatValue(item.country);
    detailsGeneration.textContent = formatValue(item.generation);
    detailsVersion.textContent = formatValue(item.version);
    detailsDisplacement.textContent = formatValue(item.displacement, " cc");
    detailsPower.textContent = formatValue(item.power, " hp");
    detailsTorque.textContent = formatValue(item.torque, " Nm");
    detailsEngineType.textContent = formatValue(item.engineType);
    detailsCylinders.textContent = formatValue(item.cylinders);
    detailsCooling.textContent = formatValue(item.cooling);
    detailsWeight.textContent = formatValue(item.weight, " kg");
    detailsFuelTankCapacity.textContent = formatValue(item.fuelTankCapacity, " L");
    detailsSeatHeight.textContent = formatValue(item.seatHeight, " mm");
    detailsTopSpeed.textContent = formatValue(item.topSpeed, " km/h");
}

// Event listeners
searchInput.addEventListener("input", updateCatalogList);

sortSelect.addEventListener("change", updateCatalogList);

authButton.addEventListener("click", () => {
    if (authenticated) {
        void logout();
    } else {
        window.location.href = "/login";
    }
});

// Initialization
void loadCatalog();
void loadAuthStatus();