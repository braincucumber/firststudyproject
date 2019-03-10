package TinkoffHomework3;

class Person {
        public String firstName;
        private String lastName;
        private String patronymic;
        private int age;
        private String gender;
        private String birthDate;
        private int inn;
        private int postCode;
        private String country;
        private String state;
        private String city;
        private String street;
        private int houseNumber;
        private int apartmentNumber;

        public String getFirstName() {
            return firstName;
        }

        public String getlastName() {
            return lastName;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public int getInn() {
            return inn;
        }

        public int getPostCode() {
            return postCode;
        }

        public String getCountry() {
            return country;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        public int getHouseNumber() {
            return houseNumber;
        }

        public int getapartmentNumber() {
            return apartmentNumber;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public void setInn(int inn) {
            this.inn = inn;
        }

        public void setPostCode(int postCode) {
            this.postCode = postCode;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }

        public void setApartmentNumber(int apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
        }
        @Override
        public String toString() {
            return String.format("firstName:%s,lastName:%s,patronymic:%s,age:%d,gender:%s,birthDate:%s,inn:%d,postcode:%d,country:%s,state:%s,city:%s,street:%s,houseNumber:%d,apartmentNumber:%d", firstName, lastName, patronymic, age, gender, birthDate, inn, postCode, country, state, city, street, houseNumber, apartmentNumber);
        }
}
