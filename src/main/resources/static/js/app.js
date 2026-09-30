console.log("Motorcycle Catalog frontend loaded");

let catalog = [];

// Catalog elements
const searchInput = document.getElementById("searchInput");
const catalogList = document.getElementById("catalogList");
const sortSelect = document.getElementById("sortSelect");
// Details elements
const detailsImage = document.getElementById("detailsImage");
const detailsName = document.getElementById("detailsName");
const detailsYear = document.getElementById("detailsYear");
// Identification
const detailsCategory = document.getElementById("detailsCategory");
const detailsCountry = document.getElementById("detailsCountry");
const detailsGeneration = document.getElementById("detailsGeneration");
const detailsVersion = document.getElementById("detailsVersion");
// Engine
const detailsDisplacement = document.getElementById("detailsDisplacement");
const detailsPower = document.getElementById("detailsPower");
const detailsTorque = document.getElementById("detailsTorque");
const detailsEngineType = document.getElementById("detailsEngineType");
const detailsCylinders = document.getElementById("detailsCylinders");
const detailsCooling = document.getElementById("detailsCooling");
// Specs
const detailsWeight = document.getElementById("detailsWeight");
const detailsFuelTank = document.getElementById("detailsFuelTank");
const detailsSeatHeight = document.getElementById("detailsSeatHeight");
const detailsTopSpeed = document.getElementById("detailsTopSpeed");

// Functions
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
    }
}

async function loadDetails(id) {
    try {
        const response = await fetch(`/api/motorcycles/${id}`);
            if (!response.ok) {
                throw new Error(`Failed to load details: ${response.status}`);
            }

        const item = await response.json();

        renderDetails(item);

    } catch (error) {
        console.error(error);
    }
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

        element.addEventListener("click", async () => {
            void loadDetails(item.id);
        });
        catalogList.appendChild(element);
    });
}

function formatValue(value, unit = "") {
    if (value == null) {
        return "—";
    }
    return `${value}${unit}`;
}

function renderDetails(item) {

    detailsImage.src = formatValue(item.imageUrl);
    detailsName.textContent = `${item.brand} ${item.model}`;
    detailsYear.textContent = `${item.year}`;
    detailsCategory.textContent = formatValue(item.category);
    detailsCountry.textContent = formatValue(item.country);
    detailsGeneration.textContent = formatValue(item.generation);
    detailsVersion.textContent = formatValue(item.version);
    detailsDisplacement.textContent = formatValue(item.displacement, " cc");
    detailsPower.textContent = formatValue(item.power, " hp");
    detailsTorque.textContent = formatValue (item.torque, " Nm");
    detailsEngineType.textContent = formatValue(item.engineType);
    detailsCylinders.textContent = formatValue(item.cylinders);
    detailsCooling.textContent = formatValue(item.cooling);
    detailsWeight.textContent = formatValue(item.weight, " kg");
    detailsFuelTank.textContent = formatValue(item.fuelTank, " L");
    detailsSeatHeight.textContent = formatValue(item.seatHeight, " mm");
    detailsTopSpeed.textContent = formatValue(item.topSpeed, " km/h");
}

// Event listeners
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

searchInput.addEventListener("input", updateCatalogList);
sortSelect.addEventListener("change", updateCatalogList);

loadCatalog();