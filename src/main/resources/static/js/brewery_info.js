
    const urlParams = new URLSearchParams(window.location.search);
    const breweryID = urlParams.get('brewery');

    fetch('https://api.openbrewerydb.org/v1/breweries/' + breweryID)
        .then(response => response.json())
        .then(brewery => {
            const formattedPhone = brewery.phone ? formatPhoneNumber(brewery.phone) : 'N/A';

            const breweryInfoDiv = document.getElementById('breweryInfo');
            breweryInfoDiv.innerHTML = `
                    <h2>${brewery.name}</h2>
                    <p>
                        Type: ${brewery.brewery_type}<br>
                        Address: ${brewery.street ? brewery.street : 'N/A'},
                                 ${brewery.city},
                                 ${brewery.state},
                                 ${brewery.postal_code}<br>
                        Website: ${brewery.website_url ? `<a href="${brewery.website_url}" target="_blank">${brewery.website_url}</a>` : 'N/A'}<br>
                        Phone: ${formattedPhone}
                    </p>
                    <button onclick="location.href='/create-review.html';">Create Review</button>`;
        })
        .catch(error => console.error('Error:', error));


    function formatPhoneNumber(phoneNumber) {
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return `(${match[1]})${match[2]}-${match[3]}`;
    }
    return null;
}
