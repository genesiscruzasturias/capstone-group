function formatPhoneNumber(phoneNumber) {
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return `(${match[1]})${match[2]}-${match[3]}`;
    }
    return null;
}

function searchBreweries() {
    const name = document.getElementById('breweryName').value;
    const city = document.getElementById('city').value;
    const state = document.getElementById('state').value;
    const type = document.getElementById('breweryType').value;

    let url = 'https://api.openbrewerydb.org/v1/breweries?';
    if (name) url += `by_name=${encodeURIComponent(name)}&`;
    if (city) url += `by_city=${encodeURIComponent(city)}&`;
    if (state) url += `by_state=${encodeURIComponent(state)}&`;
    if (type) url += `by_type=${type}&`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const resultsDiv = document.getElementById('results');
            resultsDiv.innerHTML = '<h2>Results</h2>';
            data.forEach(brewery => {
                let breweryCard = document.createElement('div');
                const formattedPhone = brewery.phone ? formatPhoneNumber(brewery.phone) : 'N/A';
                const breweryImage = brewery.website_url ? '<img src="' + getScreenshotUrl(brewery.website_url) + '" data-url="' + brewery.website_url + '" onclick="openBreweryInfo(event)" style="cursor: pointer; width: 150px; height: 150px" />' : '';

                breweryCard.innerHTML += `
                    <p>
                       <img alt="img" class="imgBtn" ${breweryImage}></img>
                        <strong>${brewery.name}</strong><br>
                        Type: ${brewery.brewery_type}<br>
                        Address: ${brewery.street ? brewery.street : 'N/A'}, 
                                 ${brewery.city}, 
                                 ${brewery.state}, 
                                 ${brewery.postal_code}<br>
                        Website: ${brewery.website_url ? `<a href="${brewery.website_url}" target="_blank">${brewery.website_url}</a>` : 'N/A'}<br>
                        Phone: ${formattedPhone}
                    </p>
                    <button>View Brewery</button>
                    `
                resultsDiv.appendChild(breweryCard);

                let viewBtn = breweryCard.querySelector("button");
                viewBtn.addEventListener("click", function () {

                    // let breweryToJavaDiv = document.getElementById('breweryForm');
                    // breweryToJavaDiv.innerHTML = `
                    //         <input th:type="text" th:field="*{name}" th:value="${brewery.name}">
                    //         <input th:type="text" th:field="*{address}" th:value="${brewery.street}">
                    //         <input th:type="text" th:field="*{phoneNumber}" th:value="${formattedPhone}">
                    //         <input th:type="text" th:field="*{city}" th:value="${brewery.city}">
                    //         <input th:type="text" th:field="*{state}" th:value="${brewery.state}">
                    //         <input th:type="text" th:field="*{postalCode}" th:value="${brewery.postal_code}">
                    //         <input th:type="text" th:field="*{url}" th:value="${brewery.website_url}">
                    // `

                    let breweryNameInput = document.getElementById('breweryNameInput');
                    breweryNameInput.value = brewery.name;
                    let breweryAddress = document.getElementById('breweryAddress');
                    breweryAddress.value = brewery.street;
                    let breweryPhone = document.getElementById('breweryPhone');
                    breweryPhone.value = formattedPhone;
                    let breweryCity = document.getElementById('breweryCity');
                    breweryCity.value = brewery.city;
                    let breweryState = document.getElementById('breweryState');
                    breweryState = brewery.state;
                    let breweryPostal = document.getElementById('breweryPostal');
                    breweryPostal.value = brewery.postal_code;
                    let breweryUrl = document.getElementById('breweryUrl');
                    breweryUrl.value = brewery.website_url;

                    console.log(brewery.name);
                    console.log(brewery.brewery_type);
                    console.log(brewery.street);
                    console.log(brewery.city);
                    console.log(brewery.state);
                    console.log(brewery.postal_code);
                    console.log(brewery.website_url);
                    console.log(formattedPhone);

                    document.forms["breweryForm"].submit();
                })







            });
        })
        .catch(error => console.error('Error:', error));
}

function getScreenshotUrl(websiteUrl) {
    return 'path/to/screenshot/service?url=' + encodeURIComponent(websiteUrl);
}

function openBreweryInfo() {
    const websiteUrl = event.target;
    console.log(websiteUrl);
    window.open('view_brewery.html?url=' + encodeURIComponent(websiteUrl), '_blank');
}