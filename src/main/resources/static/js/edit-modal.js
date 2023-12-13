document.querySelectorAll('.view-review-btn').forEach(function(button) {
    button.addEventListener('click', function() {
        // Get the review ID and description from the button's data attributes
        var reviewId = button.getAttribute('data-review-id');
        var reviewDescription = button.getAttribute('data-review-description');

        // Redirect to the view-review page using JavaScript
        window.location.href = '/view-review/' + reviewId;
    });

});
function redirectToReview(reviewId) {
    window.location.href = '/view-review/' + reviewId;
}







// let modal;
//
// document.addEventListener("DOMContentLoaded", function () {
//     modal = document.getElementById("myModal");
//     let openModalBtns = document.querySelectorAll(".open-modal-btn");
//     let span = document.querySelector(".close");
//     let editedDescriptionTextarea = document.getElementById("editedDescription");
//     let reviewIdInput = document.getElementById("reviewId");
//
//     openModalBtns.forEach(function (openModalBtn) {
//         openModalBtn.addEventListener("click", function () {
//             let reviewId = openModalBtn.getAttribute("data-review-id");
//             let editedDescription = openModalBtn.getAttribute("data-review-description");
//             console.log("Edited Description before setting:", editedDescription);
//
//             editedDescriptionTextarea.value = editedDescription;
//             reviewIdInput.value = reviewId;
//             // console.log(editedDescription)
//
//             modal.style.display = "block";
//         });
//     });
//
//     span.addEventListener("click", function () {
//         modal.style.display = "none";
//     });
//
//     window.addEventListener("click", function (event) {
//         if (event.target === modal) {
//             modal.style.display = "none";
//         }
//     });
// });
// function saveChanges() {
//     let editPostForm = document.getElementById("editPostForm");
//     let formData = new FormData(editPostForm);
//
//     fetch(editPostForm.action, {
//         method: 'POST',
//         body: formData,
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded',
//             'X-CSRF-TOKEN': document.getElementById("csrfToken").value
//         },
//         credentials: 'same-origin',
//     })
//         .then(response => {
//             console.log(editPostForm)
//             console.log(response)
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//
//             closeModal();
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
// }
//
//     function closeModal() {
//         modal.style.display = "none";
//     }
