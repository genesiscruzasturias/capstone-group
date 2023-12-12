const urlParams = new URLSearchParams(window.location.search);
const breweryID = urlParams.get('brewery');
document.getElementById('brewFormID').value = breweryID
console.log(breweryID);

fetch('https://api.openbrewerydb.org/v1/breweries/' + breweryID)
    .then(response => response.json())
    .then(brewery => {
        // document.querySelector('form').addEventListener('submit', function(event) {
        //     window.location.href = '/view-brewery?brewery=' + breweryID;
        //
        //     const breweryID = document.getElementById('brewFormID').value;
        //     document.getElementById('description').value = brewery.name;
        //
        //     // Set the brewery ID as a hidden input field in the form
        //     const breweryIDInput = document.createElement('input');
        //     breweryIDInput.type = 'hidden';
        //     breweryIDInput.name = 'breweryId'; // Use the correct field name expected by your controller
        //     breweryIDInput.value = breweryID;
        //     this.appendChild(breweryIDInput);
        //     this.submit();
        //     event.preventDefault();
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
                    </p>`
        // let submitBtn = document.getElementById('submitReviewBtn');
        // submitBtn.innerHTML = `
        //                 <a href="/view-brewery?brewery=${breweryID}">Submit</a>
        // `
        // submitBtn.addEventListener('click', function() {
        //     document.getElementById('create-review').submit();
        // })
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