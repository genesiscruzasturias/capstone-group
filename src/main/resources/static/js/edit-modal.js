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
        body: formData
    })
        .then(response => {
            if (response.ok) {

                modal.style.display = "none";

            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});