document.addEventListener("DOMContentLoaded", function () {
    var modal = document.getElementById("myModal");
    var deleteModal = document.getElementById("deleteModal");
    var openModalBtns = document.querySelectorAll(".open-modal-btn");
    var deleteModalBtns = document.querySelectorAll(".delete-modal-btn");
    var span = document.querySelector(".close");
    var deleteSpan = document.querySelector(".delete-close");
    var editedDescriptionTextarea = document.getElementById("editedDescription");
    var reviewIdInput = document.getElementById("reviewId");

    openModalBtns.forEach(function (openModalBtn) {
        openModalBtn.addEventListener("click", function () {
            var reviewId = openModalBtn.getAttribute("data-review-id");
            var editedDescription = openModalBtn.getAttribute("data-review-description");
            console.log("Edited Description before setting:", editedDescription);

            editedDescriptionTextarea.value = editedDescription;
            reviewIdInput.value = reviewId;
            modal.style.display = "block";
        });
    });

    deleteModalBtns.forEach(function (deleteModalBtn) {
        deleteModalBtn.addEventListener("click", function () {
            var reviewId = deleteModalBtn.getAttribute("data-review-id");

            // Show the confirm delete button
            document.getElementById("confirmDeleteBtn").style.display = "block";
            // Set the reviewId for deletion
            reviewIdInput.value = reviewId;

            // Display the delete modal
            deleteModal.style.display = "block";
        });
    });

    span.addEventListener("click", function () {
        modal.style.display = "none";
    });

    deleteSpan.addEventListener("click", function () {
        // Hide the confirm delete button
        document.getElementById("confirmDeleteBtn").style.display = "none";
        // Close the delete modal
        deleteModal.style.display = "none";
    });

    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});

function confirmDelete() {
    // Trigger the delete action
    var reviewId = document.getElementById("reviewId").value;

    // Use fetch to submit the delete request
    fetch(`/profile/delete-review/${reviewId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                // Optionally, you can perform additional actions after successful deletion
                console.log("Review deleted successfully");

                // Hide the confirm delete button
                document.getElementById("confirmDeleteBtn").style.display = "none";

                // Close the modal
                document.getElementById("myModal").style.display = "none";

                // Optionally, you can perform additional actions after closing the modal
            } else {
                console.error('Error deleting review');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}