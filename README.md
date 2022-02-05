# HashCash

This repo contains the implementaion of hash cash algorithm for Blockchian Technical University of Madird course.
Below is the content of the assignemnt and it's description.

<b>Introduction</b>

<p>Hashcash algorithm is a proof-of-work algorithm that was firstly used to limit email
spam. Like any other proof-of-work algorithm, it consists of requiring a certain amount
of work to be done by a user before it is allowed to perform an action. On the other hand,
the validation to check that the work has been done must be much faster than actually
doing the work.</p>

The reason why it was used to limit is because performing the calculations for a single
email is feasible, while performing them to send thousands of emails requires much time
and power consumption. Therefore, the incomes from sending spam emails do not
compensate the costs associated to sending them.
More recently, Hashcash algorithm has also been used in Blockchain systems to limit the
rate of new blocks and to make too expensive the attempts of adding invalid blocks to the
Blockchain. Furthermore, it can be used to solve the forks that may appear.
Hashcash algorithm

The algorithm consists of finding a nonce that makes that the 160-bit SHA-1 hash of a
given header starts with a given number of zeros. This is an example of header:

<ul>
   <li>ver: Hashcash format version, 1 (which supersedes version 0). </li>
   <li>bits: number of zero-bits at the start of the hashed code. </li>
   <li>date: in the format YYMMDD[hhmm[ss]].</li>
   <li>resource: resource data string being transmitted, e.g., an email address. </li>
   <li>ext: Extension (optional; ignored in version 1).</li>
   <li>rand: String of random characters, encoded in base-64 format.</li>
   <li>nonce: Binary counter, encoded in base-64 format.</li>
</ul>

Therefore, the actions that the sender must perform before sending the email are:

<ol>
   <li>To prepare a header and append a random nonce.</li>
   <li>To compute the 160-bit SHA-1 hash of the header.</li>
   <li>If the first 20 bits (or more, as specified in the header) of the hash are all zeros,
then this is an acceptable header. Finish the algorithm.</li>
   <li>If not, increment the nonce and go back to step 2. </li>
</ol>


The receiver only needs to hash the received header and check that the hash starts with
the same number of zeros as specified in the second field of the header.
1:20:1303030600:email@address.org::McMybZIhxKXu57jd:ckvi
2

<b>Assignment</b>

You have to code the algorithm from the sender’s perspective (you do not need to code
the receiver’s side). You can choose the programming language that you prefer and use
the appropriate libraries or modules that you need to obtain the hashes and to transform
numbers to base-64 format. You also need to measure the time that it takes to find an
appropriate nonce for the header. The number of leading zeros must be a parameter that
you can modify.

Once the program is working, you need to prepare a lab report about the task that you
did. In that report, you have to include the details about the decisions that you made
(programming language, libraries and modules that you used, …). You can also include
anything that you consider relevant about the lab and the work that you have done.
Moreover, you must include a performance analysis of the program when varying the
number of leading zeros that must be found

