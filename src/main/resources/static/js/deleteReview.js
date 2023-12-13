// let confirmDeleteBtn = document.getElementById("confirmDeleteBtn");
// let deleteModal = document.getElementById("deleteModal");
// let reviewIdToDelete = null;
// // let reviewId = document.getElementById('reviewId')
//
// confirmDeleteBtn.addEventListener("click", function () {
//     if (reviewIdToDelete) {
//         deleteReview(reviewIdToDelete);
//     }
// });
//
// function deleteReview(reviewId) {
//     fetch(`/profile/delete-review/${reviewId}`, {
//         method: 'DELETE',
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-TOKEN': '${_csrf.token}'
//         }
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Error deleting review');
//             }
//
//             closeDeleteModal();
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
// }
//
// function closeDeleteModal() {
//     deleteModal.style.display = "none";
// }
//
// document.addEventListener("DOMContentLoaded", function () {
//     let deleteModalBtns = document.querySelectorAll(".delete-modal-btn");
//     let reviewContentDiv = document.getElementById("reviewContent");
//     deleteModalBtns.forEach(function (deleteModalBtn) {
//         deleteModalBtn.addEventListener("click", function () {
//             let reviewDescription = deleteModalBtn.getAttribute("data-review-description");
//             reviewContentDiv.innerHTML = "<p>" + reviewDescription + "</p>";
//             reviewIdToDelete = deleteModalBtn.getAttribute("data-review-id");
//             deleteModal.style.display = "block";
//         });
//     });
//
//     window.addEventListener("click", function (event) {
//         if (event.target === deleteModal) {
//             closeDeleteModal();
//         }
//     });
// });