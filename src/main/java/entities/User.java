package entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@MappedSuperclass
@Entity
@Table(name="USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
        @NamedQuery(
                name = "getAllUsers",
                query = "SELECT u FROM User u ORDER BY u.id" // JPQL
        )
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "username is mandatory")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "password is mandatory")
    @Column(nullable = false)
    protected String password;


    //@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
  //  @ManyToMany(mappedBy = "users")
   // private Set<Message> messages;

    @NotNull
    private String email;


    public User(String username,String password, String email) {
        this.username = username;
        this.password = hashPassword(password);
     //   this.messages = messages;
        this.email = email;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }


 //   public void addMessage(Message message) {
      //  this.messages.add(message);
   // }

  //  public void removeMessage(Message message) {
  //   //   this.messages.remove(message);
  //  }

    public static String hashPassword(String password) {
        if(password == null || password.equals(""))
            return null;
        char[] encoded = null;
        try {
            ByteBuffer passwdBuffer =
                    Charset.defaultCharset().encode(CharBuffer.wrap(password));
            byte[] passwdBytes = passwdBuffer.array();
            MessageDigest mdEnc = MessageDigest.getInstance("SHA-256");
            mdEnc.update(passwdBytes, 0, password.toCharArray().length);
            encoded = new BigInteger(1, mdEnc.digest()).toString(16).toCharArray();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(encoded);
    }
/*
    public Set<Message> getMessages() {
        return messages;
    }*/
/*
    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
