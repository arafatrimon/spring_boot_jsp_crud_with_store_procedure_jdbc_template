<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>



<div class="container-fluid px-1 py-5 mx-auto">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-5 col-lg-6 col-md-7 col-9 card">
            <form action="${formAction}" method="post" modelAttribute="employee">

                <div class="mb-3">
                    <input type="hidden" class="form-control" id="id" name="id" value="${employee.id}">
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${employee.name}">

                    <c:if test="${not empty errors.name}">
                        <div class="alert alert-danger alert-dismissible fade show">
                            <strong>Error!</strong> ${errors.name}
                        </div>
                    </c:if>

                </div>
                <div class="mb-3">
                    <label for="fatherName" class="form-label">Father Name</label>
                    <input type="text" class="form-control" id="fatherName" name="fatherName" value="${employee.fatherName}">
                    <c:if test="${not empty errors.name}">
                        <div class="alert alert-danger alert-dismissible fade show">
                            <strong>Error!</strong> ${errors.fatherName}
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="motherName" class="form-label">Mother Name</label>
                    <input type="text" class="form-control" id="motherName" name="motherName" value="${employee.motherName}">
                </div>

<%--                <div class="mb-3">--%>
<%--                    <label for="gender" class="form-label">Gender</label>--%>
<%--                    <input type="text" class="form-control" id="gender" name="gender" value="${employee.gender}">--%>
<%--                </div>--%>

                <div class="mb-3">
                    <label  class="form-label">Gender</label>
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="gender" value="male"
                            <c:if test="${employee.gender=='male'}">checked</c:if>
                            >Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="gender" value="female"
                            <c:if test="${employee.gender=='female'}"> checked</c:if>
                            >Female
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="gender" value="others">Others
                        </label>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="age" class="form-label">Age</label>
                    <input type="number" class="form-control" id="age" name="age" value="${employee.age}">
                    <c:if test="${not empty errors.name}">
                        <div class="alert alert-danger alert-dismissible fade show">
                            <strong>Error!</strong> ${errors.age}
                        </div>
                    </c:if>
                </div>

<%--                <div class="mb-3">--%>
<%--                    <label for="designation" class="form-label">Designation</label>--%>
<%--                    <input type="text" class="form-control" id="designation" name="designation" value="${employee.designation}">--%>
<%--                </div>--%>

                <div class="mb-3">
                <label >Designation:</label>
                <select  class="form-control" id="sel2" name="designation" >
                    <option <c:if test="${employee.designation=='1'}">selected</c:if>>1</option>
                    <option <c:if test="${employee.designation=='2'}">selected</c:if>>2</option>
                    <option <c:if test="${employee.designation=='3'}">selected</c:if>>3</option>
                    <option <c:if test="${employee.designation=='4'}">selected</c:if>>4</option>
                    <option <c:if test="${employee.designation=='5'}">selected</c:if>>5</option>
                </select>
                </div>



                <div class="mb-3">
                    <label for="nIdNumber" class="form-label">National Id</label>
                    <input type="text" class="form-control" id="nIdNumber" name="nidNumber" value="${employee.nidNumber}">
                </div>

                <div class="mb-3">
                    <label for="dateOfBirth" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${employee.dateOfBirth}">
                </div>

                <div class="mb-3">
                    <label for="contactNumber" class="form-label">Contact Number</label>
                    <input type="text" class="form-control" id="contactNumber" name="contactNumber" value="${employee.contactNumber}">
                </div>



                <div class="mb-3">
                    <label  class="form-label">Hobby</label>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" <c:if test="${hobbyMap['option1']=='option1'}">checked</c:if> value="option1" name="hobby">Option 1
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" <c:if test="${hobbyMap['option2']=='option2'}">checked</c:if> value="option2" name="hobby">Option 2
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" <c:if test="${hobbyMap['option3']=='option3'}">checked</c:if> value="option3" name="hobby">Option 3
                    </label>
                </div>



                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${employee.address}">
                </div>

                <button type="submit" class="btn btn-primary">${pageTitle}</button>
            </form>
        </div>
    </div>
</div>

<%@include file="common/footer.jspf" %>