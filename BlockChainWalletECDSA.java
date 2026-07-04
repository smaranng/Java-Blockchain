package Blockchain;
import java.util.*;
import java.security.*;
import java.security.spec.*;
public class BlockchainWalletECDSA {
   public static void main(String[] args) {
   	try
   	{
   		int abal=100, bbal=50;
   		System.out.println("===WALLET BALANCES BEFORE TRANSACTION===\nAlice's Balance: "+abal+"\nBob's Balance: "+bbal);;
   		KeyPairGenerator keygen = KeyPairGenerator.getInstance("EC");
   		keygen.initialize(new ECGenParameterSpec("secp256r1"));
   		KeyPair keypair = keygen.generateKeyPair();
   		
   		System.out.println("Public Key: "+Base64.getEncoder().encodeToString(keypair.getPublic().getEncoded()));
   		System.out.println("Private key: "+Base64.getEncoder().encodeToString(keypair.getPrivate().getEncoded()));
   		
   		int amount=10;
   		String transaction="Send "+amount+" coins to Alice";
   		byte[] tr = transaction.getBytes("UTF-8");
   		System.out.println("\nTransaction: "+transaction);
   		
   		Signature ecdsa = Signature.getInstance("SHA256withECDSA");
   		ecdsa.initSign(keypair.getPrivate());
   		ecdsa.update(transaction.getBytes("UTF-8"));
   		String signature = Base64.getEncoder().encodeToString(ecdsa.sign());
   		System.out.println("Signature (Base64): "+signature);
   		
   		Signature verifysign = Signature.getInstance("SHA256withECDSA");
   		verifysign.initVerify(keypair.getPublic());
   		verifysign.update(tr);
   		
   		if(verifysign.verify(Base64.getDecoder().decode(signature)))
   		{
   			System.out.println("Signature Verified: True");
   			System.out.println("\n==WALLET BALANCES AFTER TRANSACTION===");
   			abal+=amount;
   			bbal-=amount;
   			System.out.println("Alice's Balance: "+abal+"\nBob's Balance: "+bbal);
   		}
   		else {
   			System.out.println("Transaction FAILED\nNo change in wallet balance");
   		}
   	}
   	catch(Exception e) {
   		e.printStackTrace();
   	}
   }
}
