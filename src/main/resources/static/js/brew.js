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

    let url = 'https://api.openbrewerydb.org/v1/breweries?per_page=10&';
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
                       <p> Brewery ID: ${brewery.id}</p>
                        <strong>${brewery.name}</strong><br>
                        Type: ${brewery.brewery_type}<br>
                        Address: ${brewery.street ? brewery.street : 'N/A'}, 
                                 ${brewery.city}, 
                                 ${brewery.state}, 
                                 ${brewery.postal_code}<br>
                        Website: ${brewery.website_url ? `<a href="${brewery.website_url}" target="_blank">${brewery.website_url}</a>` : 'N/A'}<br>
                        Phone: ${formattedPhone}
                    </p>
                    <button><a href="/view-brewery?brewery=${brewery.id}">View Brewery</a></button>
                    `
                resultsDiv.appendChild(breweryCard);

                let viewBtn = breweryCard.querySelector("button");
                viewBtn.addEventListener("click", function () {


                 })
                })
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