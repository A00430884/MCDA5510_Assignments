namespace CustomerPortal
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;
    using System.Web.Mvc;

    [Table("Customer")]
    public partial class Customer
    {

        public Customer()
        {
            this.province = "Nova Scotia";
        }


        public int ID { get; set; }

        public string firstName { get; set; }

        [StringLength(20)]
        public string lastName { get; set; }

        public string addrses1 { get; set; }

        public string city { get; set; }

        public string province { get; set; }

        public string country { get; set; }

        [PostalValidate]
        public string postal { get; set; }

        public string phone { get; set; }

        [Display(Name = "Email address")]
        [Required(ErrorMessage = "The email address is required")]
        [EmailAddress(ErrorMessage = "Invalid Email Address")]
        [RegularExpression("^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", ErrorMessage = "E-mail is not valid")]
        public string email { get; set; }

        // This property will hold all available states for selection
        public IEnumerable<SelectListItem> provinces { get; set; }

    }
}
