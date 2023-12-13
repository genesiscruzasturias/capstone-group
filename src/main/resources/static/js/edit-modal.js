let modal;

document.addEventListener("DOMContentLoaded", function () {
    modal = document.getElementById("myModal");
    let openModalBtns = document.querySelectorAll(".open-modal-btn");
    let span = document.querySelector(".close");
    let editedDescriptionTextarea = document.getElementById("editedDescription");
    let reviewIdInput = document.getElementById("reviewId");

    openModalBtns.forEach(function (openModalBtn) {
        openModalBtn.addEventListener("click", function () {
            let reviewId = openModalBtn.getAttribute("data-review-id");
            let editedDescription = openModalBtn.getAttribute("data-review-description");
            console.log("Edited Description before setting:", editedDescription);

            editedDescriptionTextarea.value = editedDescription;
            reviewIdInput.value = reviewId;

            modal.style.display = "block";
        });
    });

    span.addEventListener("click", function () {
        modal.style.display = "none";
    });

    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});

let editPostForm = document.getElementById("editPostForm");
editPostForm.addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(editPostForm);

    fetch(editPostForm.action, {
        method: 'POST',
        body: formData,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': formData.get('${_csrf.parameterName}')
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data:', data);
            // Optionally, you might want to reload the page or update the UI
            modal.style.display = "none";
        })
        .catch(error => {
            console.error('Error:', error);
        });
});